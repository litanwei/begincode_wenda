package net.begincode.core.handler;

import net.begincode.core.service.ProblemLabelService;
import org.springframework.stereotype.Component;
import net.begincode.core.param.ProblemLabelParam;
import javax.annotation.Resource;
import java.util.List;
/**
 * 问题与标签关联
 * @author Administrator
 * @date 2016年9月26日
 */
@Component
public class ProblemLabelHandler {

	@Resource
    private ProblemLabelService problemLabelService;
	
	public List<ProblemLabelParam> getLabelByLabelId(Integer labelId){
		return problemLabelService.selectAllProblemByLabel(labelId);
	}
}
