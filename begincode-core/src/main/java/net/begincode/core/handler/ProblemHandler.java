package net.begincode.core.handler;

import net.begincode.bean.Page;
import net.begincode.common.BeginCodeConstant;
import net.begincode.common.BizException;
import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.enums.*;
import net.begincode.core.model.*;
import net.begincode.core.service.*;
import net.begincode.utils.PatternUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Stay on 2016/8/26  21:41.
 */
@Component
public class ProblemHandler {
    @Resource
    private ProblemService problemService;
    @Resource
    private LabelService labelService;
    @Resource
    private MessageService messageService;
    @Resource
    private ProLabService proLabService;
    @Resource
    private AnswerService answerService;
    @Resource
    private ProAttentionService proAttentionService;
    @Resource
    private BegincodeUserService begincodeUserService;

    private HashMap<String, Integer> voteMap = new HashMap<>();         //投票map
    private HashMap<String, Integer> collectMap = new HashMap<>();    //收藏map
    public ConcurrentLinkedQueue<Integer> viewConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();     //浏览队列
    private List<Problem> viewList = new ArrayList<>();

    /**
     * 添加问题
     * 一个问题关联着   标签表(传入标签名集合)    消息表(如果传入的userId不为空 则存入消息表中)
     * 问题和标签对应的表
     *
     * @param problem 前台传入的问题
     * @param label   传入的标签对象  用于标签表的新增
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProblem(Problem problem, Label label) {
        Message message = new Message();
        problem.setTitle(HtmlUtils.htmlEscape(problem.getTitle()));
        //截取内容中@的用户名 加上url
        problem.setContent(PatternUtil.nickNameUrl(problem.getContent()));
        //创建问题如果成功返回整数
        int problemNum = problemService.createProblem(problem);
        if (problemNum < 0) {
            throw new BizException(ProblemResponseEnum.PROBLEM_ADD_ERROR);
        }
        messageService.createMessage(problem.getProblemId(),null,begincodeUserService.contentFilter(problem.getContent()));
        //发送http请求给搜索端
//        HttpUtil.createIndexHttp(problem.getProblemId());
        Set<String> labelNameSet = PatternUtil.splitName(label.getLabelName());
        //拆解标签集合,并把对应的参数传入相关表中
        operateLabelNameSet(labelNameSet, problem);
    }

    /**
     * 根据nickName查找用户
     *
     * @param nickName
     * @return
     */
    public BegincodeUser selectByNickName(String nickName) {
        return begincodeUserService.selectByNickName(nickName);
    }


    /**
     * 传入问题得到对应的标签名集合
     *
     * @param problem
     * @return
     */
    private List problemToLabel(Problem problem) {
        List list = new ArrayList<>();
        List<ProblemLabel> lt = proLabService.findByProblemId(problem.getProblemId());  //通过问题id找到问题标签集合
        //通过迭代把labelId存入集合
        for (int i = 0; i < lt.size(); i++) {
            list.add(lt.get(i).getLabelId());
        }
        List<Label> labelList = labelService.selectInById(list);  //in查询
        list.clear();
        for (Label label : labelList) {
            list.add(label.getLabelName());
        }
        return list;
    }


    /**
     * 查找新问题记录
     *
     * @return
     */
    public void selectNewProblems(Page<BizFrontProblem> page) {
        page.setTotalNum(problemService.findProblemsSize());    //问题总数
        List<Problem> problemList = problemService.findNewProblem(page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(problemList));
    }

    /**
     * 查找我的问题列表
     *
     * @param userName
     * @param page
     */
    public void selectMyProblems(String userName, Page<BizFrontProblem> page) {
        page.setTotalNum(problemService.findByNickNameProblemSize(userName));    //问题总数
        List<Problem> list = problemService.findMyProblem(userName, page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(list));
    }


    /**
     * 查找热点问题
     *
     * @param page
     * @return
     */
    public void selectHotProblems(Page<BizFrontProblem> page) {
        page.setTotalNum(problemService.findHotProSize());    //问题总数
        List<Problem> problemList = problemService.findHotProblem(page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(problemList));
    }

    /**
     * 查找未回答的问题集合
     *
     * @param page
     */
    public void selectNoAnswerProblems(Page<BizFrontProblem> page) {
        page.setTotalNum(problemService.findNoAnswerSize());    //问题总数
        List<Problem> problemList = problemService.findNoAnswerProblem(page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(problemList));
    }

    /**
     * 收藏问题集合(分页)
     *
     * @param userId
     * @param page
     */
    public void selectCollProblemsById(Integer userId, Page<BizFrontProblem> page) {
        page.setTotalNum(proAttentionService.selectCollectNumByUserId(userId));
        List<Problem> list = problemService.selCollProlemsById(userId, page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(list));
    }


    /**
     * 根据传入的问题实体 查找是否有回答的人名字
     *
     * @param problem
     * @return
     */
    public String selectOrderByProblemId(Problem problem) {
        Answer answer = answerService.findOrderByProblemId(problem.getProblemId());
        if (answer == null) {
            return null;
        } else {
            return answer.getUserName();
        }
    }


    /**
     * 传入的labelName集合 判断是否有存在
     * 如果存在加入集合
     * 不存在先加入数据库 再加入集合
     *
     * @param labelNameSet
     * @return 集合标签
     */
    private void operateLabelNameSet(Set<String> labelNameSet, Problem problem) {
        Label label = new Label();
        ProblemLabel problemLabel = new ProblemLabel();
        if (labelNameSet != null && labelNameSet.size() > 0) {
            for (String labelName : labelNameSet) {
                labelName = HtmlUtils.htmlEscape(labelName);
                Label seleLabel = labelService.selectByName(labelName);
                if (seleLabel != null) {
                    problemLabel.setLabelId(seleLabel.getLabelId());
                    problemLabel.setProblemId(problem.getProblemId());
                    proLabService.createProLab(problemLabel);
                } else {
                    label.setLabelName(HtmlUtils.htmlEscape(labelName));
                    label.setLabelName(labelName);
                    labelService.createLabel(label);
                    problemLabel.setLabelId(label.getLabelId());
                    problemLabel.setProblemId(problem.getProblemId());
                    proLabService.createProLab(problemLabel);
                }
            }
        }
    }

    /**
     * 根据id查询问题
     *
     * @param problemId
     * @return
     */
    public Problem selectById(int problemId) {
        return problemService.selProblemById(problemId);
    }

    /**
     * 传入一个问题列表返回一个分页 List 数据包
     *
     * @param problemList
     * @return
     */
    private List<BizFrontProblem> operatePage(List<Problem> problemList) {
        List<BizFrontProblem> list = new ArrayList<>();
        for (Problem problem : problemList) {
            BizFrontProblem bizFrontProblem = new BizFrontProblem();
            bizFrontProblem.setAnswerName(selectOrderByProblemId(problem));
            bizFrontProblem.setProblem(problem);
            bizFrontProblem.setLabelNameList(problemToLabel(problem));
            list.add(bizFrontProblem);
        }
        return list;
    }

    /**
     * 查找所有问题
     *
     * @return
     */
    public List<Problem> selectAllProblem() {
        return problemService.findProblemList();
    }


    /**
     * 通过nickName查找对应的问题总数
     *
     * @param nickName
     * @return
     */
    public Integer problemSizeByNickName(String nickName) {
        return problemService.findByNickNameProblemSize(nickName);
    }

    public BegincodeUser getCurrentUser(HttpServletRequest request) {
        BegincodeUser begincodeUser = CookieOperation.getUser(request);
        if (begincodeUser == null) {
            return null;
        } else {
            return begincodeUserService.findUserByOpenId(begincodeUser.getOpenId());
        }
    }


    /**
     * @param problemId
     * @return
     * @desc 问题标识查询标签列表
     */
    public List<Label> getLabelByProblemId(Integer problemId) {
        return labelService.selectLabelByProblemId(problemId);
    }

    public ProAttention selectProAttById(Integer problemId, Integer userId) {
        return proAttentionService.selectProAttentionById(problemId, userId);
    }

    /**
     * 问题查看后更改删除标识为1
     *
     * @param userId
     * @param problemId
     */
    public void updateMessageByProblemId(Integer userId, Integer problemId) {
        Integer messageNum = messageService.updateMessageByProblemId(userId, problemId);
        if (messageNum < 0) {
            throw new BizException(MessageResponseEnum.MESSAGE_UPDATE_ERROR);
        }
    }

    /**
     * 回答查找后更改删除标识为1
     *
     * @param userId
     * @param answerId
     */
    public void updateMessageByAnswerId(Integer userId, Integer answerId) {
        Integer messageNum = messageService.updateMessageByAnswerId(userId, answerId);
        if (messageNum < 0) {
            throw new BizException(MessageResponseEnum.MESSAGE_UPDATE_ERROR);
        }
    }


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
                collectMap.put(userId + "-" + problemId, Integer.parseInt(CollectEnum.NO_COLLECT.getCode()));
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
        //判断map中的状态是否是投票的状态
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
    private ProAttention findOrCreateProAtt(Integer problemId, Integer userId) {
        ProAttention proAttention = proAttentionService.selectProAttentionById(problemId, userId);
        if (proAttention == null) {
            proAttention = new ProAttention();
            proAttention.setBegincodeUserId(userId);
            proAttention.setProblemId(problemId);
            proAttention.setCollect(0);
            proAttention.setVote(0);
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
     * 从队列中更新投票状态进数据库
     */
    private void updateVoteMapToData() {
        if (voteMap.size() > 0) {
            Iterator<Map.Entry<String, Integer>> voteIterator = voteMap.entrySet().iterator();
            while (voteIterator.hasNext()) {
                Map.Entry<String, Integer> entry = voteIterator.next();
                String[] id = entry.getKey().split("-");
                Integer userId = Integer.parseInt(id[0]);
                Integer problemId = Integer.parseInt(id[1]);
                ProAttention proAttention = findOrCreateProAtt(problemId, userId);
                if (entry.getValue() == Integer.parseInt(VoteEnum.VOTE.getCode()) && proAttention.getVote() != Integer.parseInt(VoteEnum.VOTE.getCode())) {
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
        if (collectMap.size() > 0) {
            Iterator<Map.Entry<String, Integer>> collectIterator = collectMap.entrySet().iterator();
            while (collectIterator.hasNext()) {
                Map.Entry<String, Integer> entry = collectIterator.next();
                String[] id = entry.getKey().split("-");
                Integer userId = Integer.parseInt(id[0]);
                Integer problemId = Integer.parseInt(id[1]);
                ProAttention proAttention = findOrCreateProAtt(problemId, userId);
                // 更新收藏状态  要先判断数据库中有无收藏情况 如果有收藏 从problem表中加1 再更改状态
                if (entry.getValue() == Integer.parseInt(CollectEnum.COLLECT.getCode())) {
                    if (proAttention!=null && proAttention.getCollect() != Integer.parseInt(CollectEnum.COLLECT.getCode())) {
                        problemService.updateCollAddByProblemId(problemId);
                    }
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
     * 强制更新收藏和投票map进入数据库
     */
    public void updateMapToData() {
        updateCollectMapToData();
        updateVoteMapToData();
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

    /**
     * 从队列中更新map中的数据
     */
    public void forceUpdateViewToData() {
        //浏览更新进数据库
        updateViewToData();
    }


    /**
     * 从队列中更新浏览量进数据库
     */
    private void updateViewToData() {
        while (!viewConcurrentLinkedQueue.isEmpty()) {
            Problem problem = new Problem();
            problem.setProblemId(viewConcurrentLinkedQueue.poll());
            viewList.add(problem);
        }
        //批量更新浏览次数
        if (viewList.size() > 0) {
            problemService.batchUpdateView(viewList);
            viewList.clear();
            viewConcurrentLinkedQueue.clear();
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
        return viewConcurrentLinkedQueue.offer(problemId);
    }

}
