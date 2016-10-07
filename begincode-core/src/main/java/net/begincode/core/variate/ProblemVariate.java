package net.begincode.core.variate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import net.begincode.core.mapper.Bzi_ProAttentionMapper;
import net.begincode.core.mapper.ProAttentionMapper;
import net.begincode.core.model.ProAttention;
import net.begincode.core.model.ProblemsStatistical;
import net.begincode.core.service.ProAttentionService;

@Controller
public class ProblemVariate {
	private static Map<Integer, ProblemsStatistical> map;
	private static ProAttentionService proAttentionService;
	
	@Resource
	public void setProAttentionService(ProAttentionService proAttentionService) {
		ProblemVariate.proAttentionService = proAttentionService;
	}
	
	public static Map<Integer, ProblemsStatistical> getMap() {
		if(map==null){
			List<ProAttention> lsv=proAttentionService.statisticsByporblem("v");
			List<ProAttention> lsc=proAttentionService.statisticsByporblem("c");
			map=new HashMap<Integer, ProblemsStatistical>();
			for(ProAttention p:lsv){
				isInMap(p.getProblemId());
				map.get(p.getProblemId()).getVotes().add(p.getBegincodeUserId());
			}
			for(ProAttention p:lsc){
				isInMap(p.getProblemId());
				map.get(p.getProblemId()).getCollections().add(p.getBegincodeUserId());
			}
		}
		return map;
	}
	public static Boolean isInMap(int problem_id){
		//如果map中key中没有problem_id则添加新的map数据
		if(!map.keySet().contains(problem_id)){
			ProblemsStatistical problemsStatistical=new ProblemsStatistical();
			Set<Integer> collects=new HashSet<Integer>();
			Set<Integer> votes=new HashSet<Integer>();
			Integer views=proAttentionService.selectByViewCount(problem_id);
			problemsStatistical.setCollections(collects);
			problemsStatistical.setVotes(votes);
			problemsStatistical.setViews(views);
			map.put(problem_id, problemsStatistical);
			return false;
		}
		return true;
	}
	public static void updataMap(){
		Set<Integer> k=map.keySet();
		for(Integer i:k){
			Set<Integer> vs=map.get(i).getVotes();
			for(Integer u_id:vs){
				Integer resultNumber=proAttentionService.updateByProattention("v",u_id , i);
				if(resultNumber==0){
					ProAttention record=new ProAttention();
					record.setBegincodeUserId(u_id);
					record.setProblemId(i);
					record.setCollect(0);
					record.setVote(1);
					proAttentionService.insert(record);
				}
			}
			Set<Integer> cs=map.get(i).getCollections();
			for(Integer u_id:cs){
				Integer resultNumber=proAttentionService.updateByProattention(null,u_id , i);
				if(resultNumber==0){
					ProAttention record=new ProAttention();
					record.setBegincodeUserId(u_id);
					record.setProblemId(i);
					record.setCollect(1);
					record.setVote(0);
					proAttentionService.insert(record);
				}
			}
			proAttentionService.updateByProblemViewS(map.get(i).getViews(), i);
		}
	}
	
	
}
