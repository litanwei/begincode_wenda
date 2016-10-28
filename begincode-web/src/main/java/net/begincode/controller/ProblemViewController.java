package net.begincode.controller;

import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.CountMapHandler;
import net.begincode.core.handler.ProAttentionHandler;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.ProAttention;
import net.begincode.core.support.AuthPassport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Stay on 2016/10/27  19:35.
 */
@Controller
public class ProblemViewController {

    @Resource
    private CountMapHandler countMapHandler;
    @Resource
    private AccountContext accountContext;
    @Resource
    private ProAttentionHandler proAttentionHandler;


    @AuthPassport
    @RequestMapping(value = "/collect/{problemId}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateColl(HttpServletRequest request, @PathVariable(value = "problemId") Integer problemId) {
        BegincodeUser user = accountContext.getCurrentUser(request);
        Integer begincodeUserId = user.getBegincodeUserId();
        countMapHandler.initCollMap(problemId, begincodeUserId);
        countMapHandler.forceCollVoteUpdate(); //判断map中的大小是否大于5 大于则强制更新
        ProAttention proAttention = proAttentionHandler.selectProAttById(problemId, begincodeUserId);
        countMapHandler.updateCollMap();
        if (countMapHandler.getMapCollValue(proAttention.getProAttentionId()) == 0) {
            countMapHandler.addCollQueue(problemId + "-" + begincodeUserId + "-" + "1");
            return 1;
        } else if (countMapHandler.getMapCollValue(proAttention.getProAttentionId()) == 1) {
            countMapHandler.addCollQueue(problemId + "-" + begincodeUserId + "-" + "0");
            return 0;
        }
        return null;
    }

    @AuthPassport
    @RequestMapping(value = "/vote/{problemId}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateVote(HttpServletRequest request, @PathVariable(value = "problemId") Integer problemId) {
        BegincodeUser user = accountContext.getCurrentUser(request);
        Integer begincodeUserId = user.getBegincodeUserId();
        countMapHandler.initVoteMap(problemId, begincodeUserId);
        countMapHandler.forceCollVoteUpdate(); //判断map中的大小是否大于5 大于则强制更新
        ProAttention proAttention = proAttentionHandler.selectProAttById(problemId, begincodeUserId);
        countMapHandler.updateVoteMap();
        if (countMapHandler.getMapVoteValue(proAttention.getProAttentionId()) == 0) {
            countMapHandler.addVoteQueue(problemId + "-" + begincodeUserId + "-" + "1");
            return 1;
        } else if (countMapHandler.getMapVoteValue(proAttention.getProAttentionId()) == 1) {
            countMapHandler.addVoteQueue(problemId + "-" + begincodeUserId + "-" + "0");
            return 0;
        }
        return null;
    }


    @RequestMapping(value = "/view/{problemId}", method = RequestMethod.GET)
    @ResponseBody
    public Object updateView(@PathVariable(value = "problemId") int problemId) {
        countMapHandler.forceViewUpdate();  //判断浏览队列大小是否大于5，大于5则强制更新
        countMapHandler.initViewMap(problemId);
        return countMapHandler.addViewQueue(problemId);
    }
}
