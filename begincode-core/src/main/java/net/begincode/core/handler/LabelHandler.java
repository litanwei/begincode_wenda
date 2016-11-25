
package net.begincode.core.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.begincode.core.model.Label;
import net.begincode.core.model.Problem;
import net.begincode.core.service.LabelService;

import net.begincode.core.service.ProLabService;
import net.begincode.core.service.ProblemService;
import org.springframework.stereotype.Component;

/**
 * 标签事务层
 *
 * @author Administrator
 * @date 2016年9月26日
 */
@Component
public class LabelHandler {

	@Resource
    private LabelService labelService;
    @Resource
    private ProLabService proLabService;
    @Resource
    private ProblemService problemService;

    public List<Label> getAllLabel() {
        return labelService.selectAllLabel();
    }

    /**
     * @param problemId
     * @return
     * @desc 问题标识查询标签列表
     */
    public List<Label> getLabelByProblemId(Integer problemId) {
        return labelService.selectLabelByProblemId(problemId);
    }


    /**
     * 传进问题集合 返回label集合(最多十个)实体
     *
     * @param list
     * @return
     */
    public List<Label> selectLabelByLabelId(List<Problem> list) {
        List<Integer> labelIdList = proLabService.findLabelIdByProblemId(list);
        ArrayList<Label> arrayList = new ArrayList<>(labelIdList.size());
        for (int i = 0; i < labelIdList.size(); i++) {
            arrayList.add(labelService.selectById(labelIdList.get(i)));
        }
        return arrayList;
    }

    /**
     * 传进nickname返回十个label集合
     *
     * @param nickName
     * @return
     */
    public List<Label> selLabelNameListByNickName(String nickName) {
        return selectLabelByLabelId(problemService.selectProByUserName(nickName));
    }

    /**
     * 传进userId返回十个label集合
     *
     * @param userId
     * @return
     */
    public List<Label> selLabelNameListByUserId(Integer userId) {
        return selectLabelByLabelId(problemService.selectProByUserId(userId));
    }


    /**
     * 传入nickName 返回此用户标签使用次数
     * map的key为标签名 value 为使用次数
     *
     * @param nickName
     * @return
     */
    public Map<String, Integer> selLabelUseNumByNickName(String nickName) {
        Map<String, Integer> map = new HashMap<>();
        List<Label> list = selLabelNameListByNickName(nickName);
        for (int i = 0; i < list.size(); i++) {
            Integer useNum = proLabService.findNumByLabelId(list.get(i).getLabelId());
            String labelName = list.get(i).getLabelName();
            map.put(labelName, useNum);
        }
        return map;
    }

    /**
     * 传入userId 返回此用户标签使用次数
     * map的key为标签名 value 为使用次数
     *
     * @param userId
     * @return
     */
    public Map<String, Integer> selLabelUseNumByUserId(Integer userId) {
        Map<String, Integer> map = new HashMap<>();
        List<Label> list = selLabelNameListByUserId(userId);
        for (int i = 0; i < list.size(); i++) {
            Integer useNum = proLabService.findNumByLabelId(list.get(i).getLabelId());
            String labelName = list.get(i).getLabelName();
            map.put(labelName, useNum);
        }
        return map;
    }

    public Label getLabelById(Integer labelId){
        return labelService.selectById(labelId);
    }
}
