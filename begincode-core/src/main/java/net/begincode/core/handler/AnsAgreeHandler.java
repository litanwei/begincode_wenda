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
     * 根据用户id，问题回复id判断AnsAgree是否存在 存在修改内容 不存在增加
     *
     * @param：ansAgree
     * @return：
     */
    public int selectAndUpdate(AnsAgree ansAgree) {
        List<Integer> answerIdList = new ArrayList<>();
        Answer answer = answerService.selAnswerByAnswerId(ansAgree.getAnswerId());
        answerIdList.add(ansAgree.getAnswerId());
        List<AnsAgree> ansAgreeList = ansAgreeService.selectByExample(ansAgree.getBegincodeUserId(), answerIdList);
        if (ansAgreeList.size() != 0) {
            if(ansAgree.getAgree() == 1){
                answer.setAgreeCount(answer.getAgreeCount()+1);
                if(ansAgreeList.get(0).getAgree() == 2){
                    answer.setOppositionCount(answer.getOppositionCount()-1);
                }
            }else{
                if(ansAgree.getAgree() == 2){
                    answer.setOppositionCount(answer.getOppositionCount()+1);
                    if(ansAgreeList.get(0).getAgree() == 1){
                        answer.setAgreeCount(answer.getAgreeCount()-1);
                    }
                }else{
                    if(ansAgreeList.get(0).getAgree() == 1){
                        answer.setAgreeCount(answer.getAgreeCount()-1);
                    }else{
                        answer.setOppositionCount(answer.getOppositionCount()-1);
                    }
                }
            }
            answerService.updateAnswer(answer);
            return ansAgreeService.updateByExample(ansAgree);
        } else {
            if(ansAgree.getAgree()==1){
                answer.setAgreeCount(answer.getAgreeCount()+1);
            }else{
                answer.setAgreeCount(answer.getOppositionCount()+1);
            }
            answerService.updateAnswer(answer);
            return ansAgreeService.insertSelective(ansAgree);
        }
    }


    /**
     * 根据用户id，问题回复id列表获取AnsAgree列表
     *
     * @param：ansAgree
     * @return：
     */
    public Integer[] selectAnsAgreeList(BegincodeUser begincodeUser, List<Answer> answerList) {
        Integer[] agreeFlag = new Integer[answerList.size()];
        if (answerList.size() != 0 && begincodeUser != null) {
            List<Integer> answerIdList = new ArrayList<>();
            for (Answer answer : answerList) {
                answerIdList.add(answer.getAnswerId());
            }
            List<AnsAgree> ansAgreeList = ansAgreeService.selectByExample(begincodeUser.getBegincodeUserId(), answerIdList);
            for (int x = 0; x < answerList.size(); x++) {
                for (int y = 0; y < ansAgreeList.size(); y++) {
                    if (answerList.get(x).getAnswerId() == ansAgreeList.get(y).getAnswerId()) {
                        agreeFlag[x] = ansAgreeList.get(y).getAgree();
                        continue;
                    }
                }
            }
        }
        return agreeFlag;
    }
}
