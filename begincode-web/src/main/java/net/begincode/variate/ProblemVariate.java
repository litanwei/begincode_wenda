package net.begincode.variate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.springframework.stereotype.Component;

import net.begincode.core.model.LockHashMap;
import net.begincode.core.model.ProAttention;
import net.begincode.core.model.ProblemsStatistical;
import net.begincode.core.service.ProAttentionService;

@Component
public class ProblemVariate {
    private static Map<Integer, ProblemsStatistical> localmap;
    private static Map<Integer, Set<Integer>> changeMap;
    private static ProAttentionService proAttentionService;

    @Resource
    public void setProAttentionService(ProAttentionService proAttentionService) {
        ProblemVariate.proAttentionService = proAttentionService;
    }
    @Before
    public static Map<Integer, Set<Integer>> getChangeMap() {
        if (changeMap == null) {
            changeMap = new LockHashMap<Integer, Set<Integer>>();
        }
        return changeMap;
    }

    /**
     * V判断是更新Vote字段
     * C判断是Collection字段
     */
    public static Map<Integer, ProblemsStatistical> getMap() {
        if (localmap == null) {
            List<ProAttention> lsv = proAttentionService.statisticsByProblem("v");
            List<ProAttention> lsc = proAttentionService.statisticsByProblem("c");
            localmap = new LockHashMap<Integer, ProblemsStatistical>();
            for (ProAttention p : lsv) {
                isInLocalMap(p.getProblemId());
                localmap.get(p.getProblemId()).getVotes().add(p.getBegincodeUserId());
            }
            for (ProAttention p : lsc) {
                isInLocalMap(p.getProblemId());
                localmap.get(p.getProblemId()).getCollections().add(p.getBegincodeUserId());
            }
        }
        return localmap;
    }

    //判断localMap是否存在problem
    public static Boolean isInLocalMap(int problem_id) {
        //如果map中key中没有problem_id则添加新的map数据
        if (!localmap.keySet().contains(problem_id)) {
            ProblemsStatistical problemsStatistical = new ProblemsStatistical();
            Set<Integer> collects = new HashSet<Integer>();
            Set<Integer> votes = new HashSet<Integer>();
            Integer views = proAttentionService.selectByViewCount(problem_id);
            problemsStatistical.setCollections(collects);
            problemsStatistical.setVotes(votes);
            problemsStatistical.setViews(views);
            localmap.put(problem_id, problemsStatistical);
            return false;
        }
        return true;
    }

    //判断changeMap是否存在problem
    public static Boolean isInChangeMap(int problem_id) {
        if (!changeMap.keySet().contains(problem_id)) {
            Set<Integer> s = new HashSet<Integer>();
            changeMap.put(problem_id, s);
        }
        return true;
    }

    //更新本地数据
    public static void updateMap() {
        Set<Integer> k = changeMap.keySet();
        for (Integer problem_id : k) {
            Set<Integer> changes = changeMap.get(problem_id);
            for (Integer u_id : changes) {
                ProAttention p = initProAttention(u_id, problem_id);
                if (localmap.get(problem_id).getCollections().contains(u_id)) {
                    p.setCollect(1);
                }
                if (localmap.get(problem_id).getVotes().contains(u_id)) {
                    p.setVote(1);
                }
                Integer resultNumber = proAttentionService.updateByProattention(p);
                if (resultNumber == 0) {
                    proAttentionService.insert(p);
                }
            }
            proAttentionService.updateByProblemViewS(localmap.get(problem_id).getViews(),localmap.get(problem_id).getVotes().size(), localmap.get(problem_id).getCollections().size(),problem_id);
        }
        changeMap.clear();
    }

    //获得初始化ProAttention类
    public static ProAttention initProAttention(Integer user_id, Integer problem_id) {
        ProAttention initp = new ProAttention();
        initp.setBegincodeUserId(user_id);
        initp.setProblemId(problem_id);
        initp.setCollect(0);
        initp.setVote(0);
        return initp;
    }


}
