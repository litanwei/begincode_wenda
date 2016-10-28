package net.begincode.core.handler;

import net.begincode.common.BizException;
import net.begincode.core.enums.ProAttResponseEnum;
import net.begincode.core.model.ProAttention;
import net.begincode.core.model.Problem;
import net.begincode.core.service.ProAttentionService;
import net.begincode.core.service.ProblemService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Created by Stay on 2016/10/26  14:30.
 */
@Component
public class CountMapHandler {
    public ConcurrentLinkedQueue<String> voteConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<Integer> viewConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<String> collectConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    public HashMap<Integer, Integer> voteMap = new HashMap<>();
    public HashMap<Integer, Integer> viewMap = new HashMap<>();
    public HashMap<Integer, Integer> collectMap = new HashMap<>();

    @Resource
    private ProblemService problemService;
    @Resource
    private ProAttentionService proAttentionService;


    public void initViewMap(Integer problemId) {
        Problem problem = problemService.findByProblemId(problemId);
        if (viewMap.get(problemId) == null) {
            viewMap.put(problemId, problem.getViewCount());
        }
    }


    /**
     * 初始化收藏map   key对应的是收藏投票主键 value 是收藏的状态
     *
     * @param problemId
     * @param userId
     */
    public void initCollMap(Integer problemId, Integer userId) {
        ProAttention proAttention = findOrCreateProAtt(problemId, userId);
        Integer proAttentionId = proAttention.getProAttentionId();
        if (collectMap.get(proAttentionId) == null) {
            collectMap.put(proAttentionId, proAttention.getCollect());
        }
    }

    public void initVoteMap(Integer problemId, Integer userId) {
        ProAttention proAttention = findOrCreateProAtt(problemId, userId);
        Integer proAttentionId = proAttention.getProAttentionId();
        if (voteMap.get(proAttentionId) == null) {
            voteMap.put(proAttentionId, proAttention.getVote());
        }
    }

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
     * 从队列中更新map中的数据
     */
    public void updateMapToData() {
        //队列中浏览更新进数据库
        updateViewMapToData();
        //从队列中更新投票状态进数据库
        updateVoteMapToData();
        //从队列中更新收藏状态进数据库
        updateCollectMapToData();
    }


    /**
     * 从队列中更新浏览量进数据库
     */
    private void updateViewMapToData() {
        for (Integer problemId : viewConcurrentLinkedQueue) {
            viewMap.put(problemId, viewMap.get(problemId) + 1);
        }
        Iterator<Map.Entry<Integer, Integer>> viewIterator = viewMap.entrySet().iterator();
        while (viewIterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = viewIterator.next();
            problemService.updateViewByProId(entry.getKey(), entry.getValue());
        }
        viewMap.clear();
        viewConcurrentLinkedQueue.clear();
    }

    /**
     * 更新投票数据进map
     */
    public void updateVoteMap() {
        for (String vote : voteConcurrentLinkedQueue) {
            String[] str = vote.split("-");
            ProAttention proAttention = proAttentionService.selectProAttentionById(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            voteMap.put(proAttention.getProAttentionId(), Integer.parseInt(str[2]));
        }
    }

    /**
     * 更新收藏数据进map
     */
    public void updateCollMap() {
        for (String collectCount : collectConcurrentLinkedQueue) {
            String[] str = collectCount.split("-");
            ProAttention proAttention = proAttentionService.selectProAttentionById(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            collectMap.put(proAttention.getProAttentionId(), Integer.parseInt(str[2]));
        }
    }


    /**
     * 从队列中更新投票状态进数据库
     */
    private void updateVoteMapToData() {
        updateVoteMap();
        Iterator<Map.Entry<Integer, Integer>> voteIterator = voteMap.entrySet().iterator();
        while (voteIterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = voteIterator.next();
            Problem problem = problemService.selProblemById(proAttentionService.selectById(entry.getKey()).getProblemId());
            if (entry.getValue() == 1) {
                problemService.updateVoteByProId(problem.getProblemId(), problem.getVoteCount() + 1);
            } else if (entry.getValue() == 0) {
                if (proAttentionService.selectById(entry.getKey()).getVote() == 1) {
                    problemService.updateVoteByProId(problem.getProblemId(), problem.getVoteCount() - 1);
                }
            }
            proAttentionService.updateProAttVoteById(entry.getKey(), entry.getValue());
        }
        voteMap.clear();
        voteConcurrentLinkedQueue.clear();
    }

    /**
     * 更新队列中的数据进map
     */
    public void updateVoteCollQueue() {
        for (String vote : voteConcurrentLinkedQueue) {
            String[] str = vote.split("-");
            ProAttention proAttention = proAttentionService.selectProAttentionById(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            voteMap.put(proAttention.getProAttentionId(), Integer.parseInt(str[2]));
        }
        for (String collectCount : collectConcurrentLinkedQueue) {
            String[] str = collectCount.split("-");
            ProAttention proAttention = proAttentionService.selectProAttentionById(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            collectMap.put(proAttention.getProAttentionId(), Integer.parseInt(str[2]));
        }
        voteConcurrentLinkedQueue.clear();
        collectConcurrentLinkedQueue.clear();
    }


    /**
     * 从队列中更新收藏状态进数据库
     */
    private void updateCollectMapToData() {
        updateCollMap();
        Iterator<Map.Entry<Integer, Integer>> collectIterator = collectMap.entrySet().iterator();
        while (collectIterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = collectIterator.next();
            Problem problem = problemService.selProblemById(proAttentionService.selectById(entry.getKey()).getProblemId());
            if (entry.getValue() == 1) {
                problemService.updateCollByProId(problem.getProblemId(), problem.getCollectCount() + 1);
            } else if (entry.getValue() == 0) {
                if (proAttentionService.selectById(entry.getKey()).getCollect() == 1) {
                    problemService.updateCollByProId(problem.getProblemId(), problem.getCollectCount() - 1);
                }
            }
            proAttentionService.updateProAttCollectById(entry.getKey(), entry.getValue());
        }
        collectMap.clear();
        collectConcurrentLinkedQueue.clear();
    }

    /**
     * 如果map中的数据大于5 则强制更新数据
     */
    public void forceCollVoteUpdate() {
        if (voteMap.size() > 5) {
            updateVoteMapToData();
        }
        if (collectMap.size() > 5) {
            updateCollectMapToData();
        }
    }

    /**
     * 如果浏览队列中的数据多余5 则强制更新浏览进数据库
     */
    public void forceViewUpdate() {
        if (viewConcurrentLinkedQueue.size() > 5) {
            updateViewMapToData();
        }
    }

    /**
     * 投票map 根据proAttentionId查找对应的值
     *
     * @param proAttentionId
     * @return
     */
    public Integer getMapVoteValueByKey(Integer proAttentionId) {
        return voteMap.get(proAttentionId);
    }

    /**
     * 收藏map 根据proAttentionId查找对应的值
     *
     * @param proAttentionId
     * @return
     */
    public Integer getMapCollValueByKey(Integer proAttentionId) {
        return collectMap.get(proAttentionId);
    }


    /**
     *  一个问题id 增加一次浏览量
     *
     * @param problemId
     * @return
     */
    public boolean addViewQueue(Integer problemId) {
        return viewConcurrentLinkedQueue.add(problemId);
    }

    /**
     *  增加数据进收藏队列
     *  例如输入的数据为 1-1-0  第一个为问题id 第二个为用户id 最后一个为状态值
     *
     * @param data
     * @return
     */
    public boolean addCollQueue(String data){
        return collectConcurrentLinkedQueue.add(data);
    }

    /**
     * 增加数据进投票队列
     * 例如输入的数据为 1-1-0  第一个为问题id 第二个为用户id 最后一个为状态值
     *
     * @param data
     * @return
     */
    public boolean addVoteQueue(String data){
        return voteConcurrentLinkedQueue.add(data);
    }

    /**
     * 取收藏map中的值
     *
     * @param proAttentionId
     * @return
     */
    public Integer getMapCollValue(Integer proAttentionId){
        return collectMap.get(proAttentionId);
    }

    /**
     * 去投票map中的值
     *
     * @param proAttentionId
     * @return
     */
    public Integer getMapVoteValue(Integer proAttentionId){
        return voteMap.get(proAttentionId);
    }


}
