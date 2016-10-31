package net.begincode.controller;

import net.begincode.core.enums.CollectEnum;
import net.begincode.core.enums.VoteEnum;
import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.CountMapHandler;
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


    @AuthPassport
    @RequestMapping(value = "/collect/{problemId}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateColl(HttpServletRequest request, @PathVariable(value = "problemId") Integer problemId) {
        BegincodeUser user = accountContext.getCurrentUser(request);
        Integer begincodeUserId = user.getBegincodeUserId();
        countMapHandler.initCollMap(problemId, begincodeUserId);  //初始化map  如果数据库没有数据 则先存map里
        return countMapHandler.changeCollMap(begincodeUserId + "-" + problemId);   //改变map里的状态
    }

    @AuthPassport
    @RequestMapping(value = "/vote/{problemId}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateVote(HttpServletRequest request, @PathVariable(value = "problemId") Integer problemId) {
        BegincodeUser user = accountContext.getCurrentUser(request);
        Integer begincodeUserId = user.getBegincodeUserId();
        countMapHandler.initVoteMap(problemId, begincodeUserId);   //初始化map  如果数据库中没有数据 则先存map里
        return countMapHandler.changVoteMap(begincodeUserId + "-" + problemId);  //改变map里的状态
    }


    /**
     * 问题浏览增加队列
     *
     * @param problemId
     * @return
     */
    @RequestMapping(value = "/view/{problemId}", method = RequestMethod.GET)
    @ResponseBody
    public Object updateView(@PathVariable(value = "problemId") int problemId) {
        return countMapHandler.addViewQueue(problemId);
    }
}
