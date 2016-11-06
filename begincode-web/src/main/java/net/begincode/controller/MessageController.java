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
     * 获得message总数据数
     * @param request
     * @return
     */
    @AuthPassport
    @RequestMapping(value="/count",method=RequestMethod.POST)
    @ResponseBody
    public Object countMessage(HttpServletRequest request){
        BegincodeUser begincodeUser;
        begincodeUser=accountContext.getCurrentUser(request);
        return messageHandler.countByMessageRemind(begincodeUser.getBegincodeUserId());
    }

}
