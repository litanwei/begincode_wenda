package net.begincode.controller;

import net.begincode.core.handler.AccountContext;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.ProblemsStatistical;
import net.begincode.variate.ProblemVariate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequestMapping("/problem")
@Controller
public class Biz_ProblemController {
	@Resource
	private AccountContext accountContext;
	private Map<Integer, ProblemsStatistical> localmap;
	private Map<Integer, Set<Integer>> changeMap;
	/*
	 * 进行问题统计
	 */
	@RequestMapping(value = "/{status}/{order}/{problem_id}")
	@ResponseBody
	public Object ProblemStatisticals(@PathVariable("status") String status, @PathVariable("order") String order,
			@PathVariable("problem_id") Integer problem_id, HttpServletRequest request) {
		initMap();
		ProblemVariate.isInLocalMap(problem_id); // 判断是否为map存在的问题 不存在就自动put进去一个新的数据
		ProblemVariate.isInChangeMap(problem_id); //判断是否存在改变的problem
		ProblemsStatistical p = localmap.get(problem_id); // 获得当前problem 的操作类 方便操作
		Map<String, Object> reData = getReMap(p); // 获得初始化的返回类
		BegincodeUser begincodeUser = new BegincodeUser();
		try {
			begincodeUser = accountContext.getCurrentUser(request);
		} catch (Exception e) {
			return reData;
		}
		if (status.equals("click")) { // 点击事件
			if (order.equals("vote")) {
				if (p.getVotes().contains(begincodeUser.getBegincodeUserId())) {
					p.getVotes().remove(begincodeUser.getBegincodeUserId());
					reData.put("voteNumber", p.getVotes().size());
				} else {
					p.getVotes().add(begincodeUser.getBegincodeUserId());
					reData.put("voteNumber", p.getVotes().size());
				}
			} else if (order.equals("collection")) {
				if (p.getCollections().contains(begincodeUser.getBegincodeUserId())) {
					p.getCollections().remove(begincodeUser.getBegincodeUserId());
					reData.put("collectionNumber", p.getCollections().size());
				} else {
					p.getCollections().add(begincodeUser.getBegincodeUserId());
					reData.put("collectionNumber", p.getCollections().size());
				}
			}
			changeMap.get(problem_id).add(begincodeUser.getBegincodeUserId()); //添加改变的uid
		}
		// 加载事件
		if (p.getVotes().contains(begincodeUser.getBegincodeUserId())) {
			reData.put("vote", true);
		}
		if (p.getCollections().contains(begincodeUser.getBegincodeUserId())) {
			reData.put("collection", true);
		}
		return reData;

	}
	/*
	 * 进行problem 浏览统计
	 */
	@RequestMapping(value="/view/{problem_id}")
	@ResponseBody
	public Object problemViews(@PathVariable("problem_id") Integer problem_id,HttpSession httpSession){
		initMap();
		ProblemVariate.isInLocalMap(problem_id);
		if(httpSession.getAttribute("problem_id"+problem_id)==null){
			httpSession.setAttribute("problem_id"+problem_id, "isView");
			localmap.get(problem_id).setViews(localmap.get(problem_id).getViews()+1);
		}
		return localmap.get(problem_id).getViews();
	}
	
	//获取基础操作类
	public Map<String, Object> getReMap(ProblemsStatistical p) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vote", false);
		map.put("collection", false);
		map.put("voteNumber", p.getVotes().size());
		map.put("collectionNumber", p.getCollections().size());
		map.put("viewNumber", p.getViews());
		return map;
	}
	//初始化map
	public void initMap(){
		if(localmap==null){
			localmap=ProblemVariate.getMap();
		}
		if(changeMap==null){
			changeMap=ProblemVariate.getChangeMap();
		}
		
	}
}
