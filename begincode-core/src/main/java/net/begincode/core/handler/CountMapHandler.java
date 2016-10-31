package net.begincode.core.handler;

import net.begincode.common.BeginCodeConstant;
import net.begincode.common.BizException;
import net.begincode.core.enums.CollectEnum;
import net.begincode.core.enums.ProAttResponseEnum;
import net.begincode.core.enums.VoteEnum;
import net.begincode.core.model.ProAttention;
import net.begincode.core.model.Problem;
import net.begincode.core.service.ProAttentionService;
import net.begincode.core.service.ProblemService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Created by Stay on 2016/10/26  14:30.
 */
@Component
public class CountMapHandler {
    public ConcurrentLinkedQueue<Integer> viewConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();     //浏览队列
    public HashMap<String, Integer> voteMap = new HashMap<>();         //投票map
    public List<Problem> viewList = new ArrayList<>();
    public HashMap<String, Integer> collectMap = new HashMap<>();    //收藏map

    @Resource
    private ProblemService problemService;
    @Resource
    private ProAttentionService proAttentionService;


    /**
     * 初始化收藏map  key为userId-problemId value为状态
     * 比如 1-2  0   说明用户主键为1 问题为2 收藏状态为0
     *
     * @param problemId
     * @param userId
     */
    public void initCollMap(Integer problemId, Integer userId) {
        forceCollVoteUpdate(); //判断map中的大小是否大于指定值 大于则强制更新
        ProAttention proAttention = findProAttrById(problemId, userId);    //先从数据库中查找是否有值 有则返回 没有则返回null
        if (proAttention == null) {
            if (collectMap.get(userId + "-" + problemId) == null) {
                collectMap.put(userId + "-" + problemId,Integer.parseInt(CollectEnum.NO_COLLECT.getCode()));
            }
        } else {
            if (collectMap.get(userId + "-" + problemId) == null) {
                collectMap.put(userId + "-" + problemId, proAttention.getCollect());
            }
        }
    }

    /**
     * 每次调用 传入对应的key 改变里面的value状态
     *
     * @param strId
     * @return
     */
    public String changeCollMap(String strId) {
        if (collectMap.get(strId) == Integer.parseInt(CollectEnum.COLLECT.getCode())) {
            collectMap.put(strId, Integer.parseInt(CollectEnum.NO_COLLECT.getCode()));
            return "0";
        } else {
            collectMap.put(strId, Integer.parseInt(CollectEnum.COLLECT.getCode()));
            return "1";
        }
    }


    /**
     * 初始化投票map
     *
     * @param problemId
     * @param userId
     * @return
     */
    public void initVoteMap(Integer problemId, Integer userId) {
        forceCollVoteUpdate(); //判断map中的大小是否大于指定值 大于则强制更新
        ProAttention proAttention = findProAttrById(problemId, userId);
        if (proAttention == null) {
            if (voteMap.get(userId + "-" + problemId) == null) {
                voteMap.put(userId + "-" + problemId, Integer.parseInt(VoteEnum.NO_VOTE.getCode()));
            }
        } else {
            if (voteMap.get(userId + "-" + problemId) == null) {
                voteMap.put(userId + "-" + problemId, proAttention.getVote());
            }
        }
    }

    /**
     * 改变map里的状态
     *
     * @param strId
     * @return
     */
    public String changVoteMap(String strId) {
        if (voteMap.get(strId) == Integer.parseInt(VoteEnum.VOTE.getCode())) {
            voteMap.put(strId, Integer.parseInt(VoteEnum.NO_VOTE.getCode()));
            return "0";
        } else {
            voteMap.put(strId, Integer.parseInt(VoteEnum.VOTE.getCode()));
            return "1";
        }
    }


    /**
     * 查找实体 如果未找到 则创建
     *
     * @param problemId
     * @param userId
     * @return
     */
    @Transactional
    public ProAttention findOrCreateProAtt(Integer problemId, Integer userId) {
        ProAttention proAttention = proAttentionService.selectProAttentionById(problemId, userId);
        if (proAttention == null) {
            proAttention = new ProAttention();
            proAttention.setBegincodeUserId(userId);
            proAttention.setProblemId(problemId);
            Integer createNum = proAttentionService.createProAtt(proAttention);
            if (createNum < 0) {
                throw new BizException(ProAttResponseEnum.PROATT_CREATE_ERROR);
            }
            return proAttention;
        }
        return proAttention;
    }

    /**
     * 如果数据库不存在数据 则返回null
     *
     * @param problemId
     * @param userId
     * @return
     */
    public ProAttention findProAttrById(Integer problemId, Integer userId) {
        return proAttentionService.selectProAttentionById(problemId, userId);
    }


    /**
     * 从队列中更新map中的数据
     */
    public void updateMapToData() {
        //浏览更新进数据库
        updateViewToData();
        //更新投票状态进数据库
        updateVoteMapToData();
        //更新收藏状态进数据库
        updateCollectMapToData();
    }


    /**
     * 从队列中更新浏览量进数据库
     */
    private void updateViewToData() {
        if(viewConcurrentLinkedQueue.size()>0){
            for (Integer problemId : viewConcurrentLinkedQueue) {
                Problem problem = new Problem();
                problem.setProblemId(problemId);
                viewList.add(problem);
            }
            //批量更新浏览次数
            problemService.batchUpdateView(viewList);
            viewList.clear();
            viewConcurrentLinkedQueue.clear();
        }
    }


    /**
     * 从队列中更新投票状态进数据库
     */
    private void updateVoteMapToData() {
        if(voteMap.size()>0){
            Iterator<Map.Entry<String, Integer>> voteIterator = voteMap.entrySet().iterator();
            while (voteIterator.hasNext()) {
                Map.Entry<String, Integer> entry = voteIterator.next();
                String[] id = entry.getKey().split("-");
                Integer userId = Integer.parseInt(id[0]);
                Integer problemId = Integer.parseInt(id[1]);
                ProAttention proAttention = findOrCreateProAtt(problemId, userId);
                if (entry.getValue() == Integer.parseInt(VoteEnum.VOTE.getCode())) {
                    problemService.updateVoteAddByProblemId(problemId);
                } else if (entry.getValue() == Integer.parseInt(VoteEnum.NO_VOTE.getCode())) {
                    if (proAttention.getVote() == Integer.parseInt(VoteEnum.VOTE.getCode())) {
                        problemService.updateVoteReduceByProblemId(problemId);
                    }
                }
                proAttentionService.updateProAttVoteById(proAttention.getProAttentionId(), entry.getValue());
            }
            voteMap.clear();
        }
    }


    /**
     * 从队列中更新收藏状态进数据库
     */
    private void updateCollectMapToData() {
        if(collectMap.size()>0){
            Iterator<Map.Entry<String, Integer>> collectIterator = collectMap.entrySet().iterator();
            while (collectIterator.hasNext()) {
                Map.Entry<String, Integer> entry = collectIterator.next();
                String[] id = entry.getKey().split("-");
                Integer userId = Integer.parseInt(id[0]);
                Integer problemId = Integer.parseInt(id[1]);
                ProAttention proAttention = findOrCreateProAtt(problemId, userId);
                // 更新收藏状态  要先判断数据库中有无收藏情况 如果有收藏 从problem表中加1 再更改状态
                if (entry.getValue() == Integer.parseInt(CollectEnum.COLLECT.getCode())) {
                    problemService.updateCollAddByProblemId(problemId);
                } else if (entry.getValue() == Integer.parseInt(CollectEnum.NO_COLLECT.getCode())) {
                    if (proAttention.getCollect() == Integer.parseInt(CollectEnum.COLLECT.getCode())) {
                        problemService.updateCollReduceByProblemId(problemId);
                    }
                }
                proAttentionService.updateProAttCollectById(proAttention.getProAttentionId(), entry.getValue());
            }
            collectMap.clear();
        }
    }

    /**
     * 如果map中的数据大于指定值 则强制更新数据
     */
    public void forceCollVoteUpdate() {
        if (voteMap.size() > BeginCodeConstant.UPDATE_VALUE) {
            updateVoteMapToData();
        }
        if (collectMap.size() > BeginCodeConstant.UPDATE_VALUE) {
            updateCollectMapToData();
        }
    }

    /**
     * 如果浏览队列中的数据多余5 则强制更新浏览进数据库
     */
    public void forceViewUpdate() {
        if (viewConcurrentLinkedQueue.size() > BeginCodeConstant.UPDATE_VALUE) {
            updateViewToData();
        }
    }


    /**
     * 一个问题id 增加一次浏览量
     *
     * @param problemId
     * @return
     */
    public boolean addViewQueue(Integer problemId) {
        //判断队列中的数据是否大于指定的值
        forceViewUpdate();
        return viewConcurrentLinkedQueue.add(problemId);
    }


    /**
     * 取收藏map中的值
     *
     * @param strId
     * @return
     */
    public Integer getMapCollValue(String strId) {
        return collectMap.get(strId);
    }

    /**
     * 取投票map中的值
     *
     * @param strId
     * @return
     */
    public Integer getMapVoteValue(String strId) {
        return voteMap.get(strId);
    }


}
