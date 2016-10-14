package net.begincode.core.handler;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.model.Problem;
import net.begincode.core.service.ProblemLabelService;

import org.springframework.stereotype.Component;
/**
 * 问题与标签关联
 * @author Administrator
 * @date 2016年9月26日
 */
@Component
public class ProblemLabelHandler {

	@Resource
    private ProblemLabelService problemLabelService;
	
	public List<Problem> getLabelByLabelId(Integer labelId){
		return problemLabelService.selectAllLabel(labelId);
	}
}
