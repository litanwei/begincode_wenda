package net.begincode.core.handler;

import net.begincode.core.model.AnsAgree;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.AnsAgreeService;
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

    /**
     * 根据用户id，问题回复id判断AnsAgree是否存在 存在修改内容 不存在增加
     *
     * @param：ansAgree
     * @return：
     */
    public int selectAndUpdate(AnsAgree ansAgree) {
        List<Integer> answerIdList = new ArrayList<>();
        answerIdList.add(ansAgree.getAnswerId());
        List<AnsAgree> ansAgreeList = ansAgreeService.selectByExample(ansAgree.getBegincodeUserId(), answerIdList);
        if (ansAgreeList.size() != 0) {
            return ansAgreeService.updateByExample(ansAgree);
        } else {
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
        for (int i = 0; i < agreeFlag.length; i++) {
            agreeFlag[i]=0;
        }
        if (answerList.size() != 0 && begincodeUser != null) {
            List<Integer> answerIdList = new ArrayList<>();
            for (Answer answer : answerList) {
                answerIdList.add(answer.getAnswerId());
            }
            List<AnsAgree> ansAgreeList = ansAgreeService.selectByExample(begincodeUser.getBegincodeUserId(), answerIdList);
            for (int x = 0;x < answerList.size(); x++) {
                for (int y = 0; y < ansAgreeList.size(); y++) {
                    int a = answerList.get(x).getAnswerId();
                    int b = ansAgreeList.get(y).getAnswerId();
                    if (a == b) {
                        agreeFlag[x] = ansAgreeList.get(y).getAgree();
                        continue;
                    }
                }
            }
        }
        return agreeFlag;
    }
}
