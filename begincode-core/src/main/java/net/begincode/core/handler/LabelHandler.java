
package net.begincode.core.handler;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.model.Label;
import net.begincode.core.service.LabelService;

import org.springframework.stereotype.Component;
/**
 * 标签事务层
 * @author Administrator
 * @date 2016年9月26日
 */
@Component
public class LabelHandler {

	@Resource
    private LabelService labelService;
	
	/**
	 * 获取所有标签
	 * @return
	 */
	public List<Label> getAllLabel(){
		return labelService.selectAllLabel();
	}

	/**
	 * @desc 问题标识查询标签列表
	 * @param problemId
	 * @return
	 */
	public List<Label> getLabelByProblemId(Integer problemId){
		return labelService.selectLabelByProblemId(problemId);
	}

	/**
	 * 通过LabelId获取LabelName
	 * @param labelId
	 * @return
	 */
	public String getLabelById(Integer labelId){
		return labelService.selectLabelById(labelId);
	}
}
