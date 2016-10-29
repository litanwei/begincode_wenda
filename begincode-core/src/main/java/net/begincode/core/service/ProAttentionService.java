package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.model.ProAttentionExample;
import org.springframework.stereotype.Service;

import net.begincode.core.mapper.Biz_ProAttentionMapper;
import net.begincode.core.mapper.ProAttentionMapper;
import net.begincode.core.model.ProAttention;

@Service
public class ProAttentionService {
    @Resource
    private Biz_ProAttentionMapper biz_ProAttentionMapper;
    @Resource
    private ProAttentionMapper proAttentionMapper;

    /**
     * 根据userId返回用户收藏的问题总数
     *
     * @param userId
     * @return
     */
    public int selectCollectNumByUserId(Integer userId) {
        return biz_ProAttentionMapper.selectCollectNumByUserId(userId);
    }

    /**
     * 根据问题号和用户号修改 未收藏 已收藏状态
     *
     * @param problemId
     * @param userId
     * @param vote
     * @return
     */
    public Integer updateVoteByProIdAndUserId(Integer problemId, Integer userId, Integer vote) {
        ProAttention proAttention = new ProAttention();
        proAttention.setVote(vote);
        ProAttentionExample proAttentionExample = new ProAttentionExample();
        proAttentionExample.createCriteria().andProblemIdEqualTo(problemId).andBegincodeUserIdEqualTo(userId);
        return proAttentionMapper.updateByExampleSelective(proAttention, proAttentionExample);
    }

    /**
     * 通过问题id 和用户id 查找
     *
     * @param problemId
     * @param userId
     * @return
     */
    public ProAttention selectProAttentionById(Integer problemId, Integer userId) {
        ProAttentionExample proAttentionExample = new ProAttentionExample();
        proAttentionExample.createCriteria().andBegincodeUserIdEqualTo(userId).andProblemIdEqualTo(problemId);
        List<ProAttention> list = proAttentionMapper.selectByExample(proAttentionExample);
        if (list.size() > 0 && list != null) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过标识 更改传入的投票状态
     *
     * @param proAttenId
     * @param vote
     * @return
     */
    public Integer updateProAttVoteById(Integer proAttenId, Integer vote) {
        ProAttention proAttention = new ProAttention();
        proAttention.setVote(vote);
        ProAttentionExample proAttentionExample = new ProAttentionExample();
        proAttentionExample.createCriteria().andProAttentionIdEqualTo(proAttenId);
        return proAttentionMapper.updateByExampleSelective(proAttention, proAttentionExample);
    }

    /**
     * 通过标识 更新收藏状态
     *
     * @param proAttenId
     * @param collect
     * @return
     */
    public Integer updateProAttCollectById(Integer proAttenId, Integer collect) {
        ProAttention proAttention = new ProAttention();
        proAttention.setCollect(collect);
        ProAttentionExample proAttentionExample = new ProAttentionExample();
        proAttentionExample.createCriteria().andProAttentionIdEqualTo(proAttenId);
        return proAttentionMapper.updateByExampleSelective(proAttention, proAttentionExample);
    }

    /**
     * 创建新的一条数据
     *
     * @param proAttention
     * @return
     */
    public Integer createProAtt(ProAttention proAttention) {
        return proAttentionMapper.insertSelective(proAttention);
    }

    /**
     * 根据主键返回实体
     *
     * @param proAttentionId
     * @return
     */
    public ProAttention selectById(Integer proAttentionId) {
        return proAttentionMapper.selectByPrimaryKey(proAttentionId);
    }


}
