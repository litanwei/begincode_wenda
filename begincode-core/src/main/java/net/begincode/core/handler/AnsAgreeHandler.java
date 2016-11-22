package net.begincode.core.handler;

import net.begincode.core.model.AnsAgree;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.AnsAgreeService;
import net.begincode.core.service.AnswerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saber on 2016/11/4.
 */
@Component
public class AnsAgreeHandler {

    @Resource
    private AnsAgreeService ansAgreeService;
    @Resource
    private AnswerService answerService;


    /**
     * 根据用户id，问题回复id,agree来更新answer赞同数和ansAgree或插入ansAgree
     *
     * @param：ansAgree
     * @return：
     */
    public  void  updateAnswerAgrCountAndAnsAgree(AnsAgree ansAgree) {
        int ansAgreeNum = ansAgreeService.selectAndUpdate(ansAgree);
        if (ansAgreeNum != ansAgree.getAgree() && ansAgree.getAgree()!= 2) {
            if (ansAgreeNum == 2) {
                //反对数减一
                answerService.updateOppoCountByAnswerId(ansAgree.getAnswerId(), ansAgree.getAgree());
            }
            //赞同由service处理 对应service处理机制 ansAgree.getAgree()为0，赞同数减1；为1，赞同数加1。
            if(ansAgree.getAgree() != 0 || ansAgreeNum == 1) {
                answerService.updateAgrCountByAnswerId(ansAgree.getAnswerId(), ansAgree.getAgree());
            }
        }
    }

    /**
     * 根据用户id，问题回复id,agree来更新answer反对数和ansAgree或插入ansAgree
     *
     * @param：ansAgree
     * @return：
     */
    public void updateAnswerOppoCountAndAnsAgree(AnsAgree ansAgree) {
        int ansAgreeNum = ansAgreeService.selectAndUpdate(ansAgree);
        if (ansAgreeNum != ansAgree.getAgree() && ansAgree.getAgree()!= 1) {
            if (ansAgreeNum == 1) {
                //赞同数减一
                answerService.updateAgrCountByAnswerId(ansAgree.getAnswerId(), ansAgree.getAgree());
            }
            if(ansAgree.getAgree() != 0 || ansAgreeNum == 2) {
                //赞同由service处理 对应service处理机制 ansAgree.getAgree()为0，反对数减1；为2，反对数加1。
                answerService.updateOppoCountByAnswerId(ansAgree.getAnswerId(), ansAgree.getAgree());
            }
        }
    }

}
