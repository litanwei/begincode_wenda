package net.begincode.core.handler;

import net.begincode.core.model.Label;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.model.ProblemLabelExample;
import net.begincode.core.service.LabelService;
import net.begincode.core.service.ProblemLabelService;
import net.begincode.core.service.ProblemService;
import org.springframework.stereotype.Component;

import net.begincode.core.param.LabelAndProblemId;
import net.begincode.core.param.ProblemLabelParam;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 问题与标签关联
 * @author Administrator
 * @date 2016年9月26日
 */
@Component
public class ProblemLabelHandler {

	@Resource
    private ProblemLabelService problemLabelService;
	@Resource
	private LabelService labelService;
	@Resource
	private ProblemService problemService;
	
//	public List<ProblemLabelParam> getLabelByLabelId(Integer labelId){
//
//		//通过labelId查proLabel的所有对应关系
//		List<ProblemLabel> proLabel = problemLabelService.getPrlblemLabelByLabelId(labelId);
//		//通过proLabel的关联查所有的problem数组
//		List<ProblemLabelParam> pro = new ArrayList<ProblemLabelParam>();
//		if(proLabel != null){
//			for (ProblemLabel problemLabel : proLabel) {
//				Problem problem = problemService.selProblemById(problemLabel.getProblemId());
//				if(null != problem){
//					ProblemLabelParam problemLabelParam = new ProblemLabelParam();
//					problemLabelParam.setProblem(problem);
//					problemLabelParam.setLabell(labelService.selectLabelByProblemId(problem.getProblemId()));
//					pro.add(problemLabelParam);
//				}
//			}
//		}
//		return pro;
//	}
	//---------------------------优化(减少一次查询数据库)-----------------------
	public List<ProblemLabelParam> getLabelByLabelId(Integer labelId){
		//通过proLabel的关联查所有的problem数组
		List<ProblemLabelParam> pro = new ArrayList<ProblemLabelParam>();
		List<Problem> problems=problemService.selectByProblemLabel(labelId);
		List<Integer> problemIds=new ArrayList<>();
		for(Problem p:problems){
			problemIds.add(p.getProblemId());
		}
		List<LabelAndProblemId> labelAndProblemId=labelService.selectLabelAndProblemIdByProblemId(problemIds);
		Map<Integer,List<Label>> map=new HashMap<>();
		for(LabelAndProblemId lp:labelAndProblemId){
			if(map.containsKey(lp.getProblemId())){
				map.get(lp.getProblemId()).add((Label)lp);
			}
    	}
		for(Problem problem:problems){
			ProblemLabelParam problemLabelParam=new ProblemLabelParam();
			problemLabelParam.setProblem(problem);
			problemLabelParam.setLabell(map.get(problem.getProblemId()));
			pro.add(problemLabelParam);
		}
		return pro;
	}
}
