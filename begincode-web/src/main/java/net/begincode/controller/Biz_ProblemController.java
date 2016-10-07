package net.begincode.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.begincode.core.handler.AccountContext;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.ProblemsStatistical;
import net.begincode.core.support.AuthPassport;
import net.begincode.core.variate.ProblemVariate;

@RequestMapping("/problem")
@Controller
public class Biz_ProblemController {
	@Resource
	private AccountContext accountContext;

	@RequestMapping(value = "/{status}/{order}/{problem_id}")
	@ResponseBody
	public Object ProblemStatisticals(@PathVariable("status") String status, @PathVariable("order") String order,
			@PathVariable("problem_id") int problem_id, HttpServletRequest request) {
		System.out.println(order);
		System.out.println(problem_id);

		Map<Integer, ProblemsStatistical> localmap = ProblemVariate.getMap(); // 获得本地map
		ProblemVariate.isInMap(problem_id); // 判断是否为map存在的问题 不存在就自动put进去一个新的数据
		ProblemsStatistical p = localmap.get(problem_id); // 获得当前problem 的操作类
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
		} // 加载事件
		if (p.getVotes().contains(begincodeUser.getBegincodeUserId())) {
			reData.put("vote", true);
		}
		if (p.getCollections().contains(begincodeUser.getBegincodeUserId())) {
			reData.put("collection", true);
		}
		return reData;

	}

	public Map<String, Object> getReMap(ProblemsStatistical p) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vote", false);
		map.put("collection", false);
		map.put("voteNumber", p.getVotes().size());
		map.put("collectionNumber", p.getCollections().size());
		map.put("viewNumber", p.getViews());
		return map;
	}
}
