package net.begincode.controller;

import net.begincode.bean.Page;
import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.MessageHandler;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.support.AuthPassport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/message")
@Controller
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private MessageHandler messageHandler;
    @Resource
    private AccountContext accountContext;

    /**
     * 消息入口 @ 我的消息列表
     *
     * @param request
     * @param currentPage
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object findMessage(HttpServletRequest request, @RequestParam(value = "page") Integer currentPage) {
        Page<Object> page = new Page<>();
        page.setCurrentNum(currentPage);
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        messageHandler.selectMessageByUserId(begincodeUser.getBegincodeUserId(), page);
        return page;
    }

    /**
     * 进入此方法 根据问题id 更改消息表中的删除状态 为已读
     *
     * @param request
     * @param problemId
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/problem/{id}", method = RequestMethod.GET)
    public Object updateMessageByProblemId(HttpServletRequest request, @PathVariable(value = "id") Integer problemId) {
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        messageHandler.updateMessageByProblemId(begincodeUser.getBegincodeUserId(), problemId);
        return "redirect:/problem/" + problemId + ".htm";
    }

    /**
     * 进入此方法 根据回答id 更改消息表中的删除状态 为已读
     *
     * @param request
     * @param problemId
     * @param answerId
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/answer/{id}", method = RequestMethod.GET)
    public Object updateMessageByAnswerId(HttpServletRequest request, @PathVariable(value = "id") Integer problemId, @RequestParam(value = "answerId") Integer answerId) {
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        messageHandler.updateMessageByAnswerId(begincodeUser.getBegincodeUserId(), answerId);
        return "redirect:/problem/" + problemId + ".htm";
    }


}
