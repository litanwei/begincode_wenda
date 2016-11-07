package net.begincode.core.handler;

import net.begincode.bean.Page;
import net.begincode.common.BizException;
import net.begincode.core.enums.MessageResponseEnum;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.Message;
import net.begincode.core.model.Problem;
import net.begincode.core.service.AnswerService;
import net.begincode.core.service.BegincodeUserService;
import net.begincode.core.service.MessageService;
import net.begincode.core.service.ProblemService;
import net.begincode.utils.JsoupUtil;
import net.begincode.utils.PatternUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
public class MessageHandler {
    @Resource
    private MessageService messageService;

    @Resource
    private BegincodeUserService begincodeUserService;
    @Resource
    private AnswerService answerService;
    @Resource
    private ProblemService problemService;




    /**
     * 根据用户id查找消息表中的数据  用Object实体集合接收 返回的虽然是Object 但是object里面包含了问题和回答
     *
     * @param userId
     * @param page
     */
    public void selectMessageByUserId(Integer userId, Page<Object> page) {
        page.setTotalNum(messageService.findMessSize(userId));
        List<Message> list = messageService.findMessByUserId(userId, page.getCurrentNum(), page.getPageEachSize());
        ArrayList<Object> arrayList = new ArrayList<>();
        /**
         * 迭代list 查看有无对应的问题id或者回答id
         * 如果问题id不为0 则查找对应的问题
         * 如果回答id不为0 则查找对应的回答
         * 全部都存入到arrayList中
         */
        for (int i = 0; i < list.size(); i++) {
            Message message = list.get(i);
            if (message.getAnswerId() != 0) {
                Answer answer = answerService.findByAnswerId(message.getAnswerId());
                String content = JsoupUtil.replaceContent(answer.getContent());
                if (content.length() > 200) {
                    answer.setContent(content.substring(0, 200));
                } else {
                    answer.setContent(content);
                }
                arrayList.add(answer);
            } else {
                Problem problem = problemService.selProblemById(message.getProId());
                String content = JsoupUtil.replaceContent(problem.getContent());
                if (content.length() > 200) {
                    problem.setTitle(content.substring(0, 200));
                } else {
                    problem.setTitle(content);
                }
                problem.setContent(null);
                arrayList.add(problem);
            }
        }
        page.setData(arrayList);
    }



    /**
     * 获得message数量
     * @return
     */
    public int countByMessageRemind(Integer user_id){
        return messageService.findMessSize(user_id);
    }



}
