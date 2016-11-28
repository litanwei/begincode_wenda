package net.begincode.core.service;

import net.begincode.core.enums.DeleteFlagEnum;
import net.begincode.core.mapper.BizLabelMapper;
import net.begincode.core.mapper.LabelMapper;
import net.begincode.core.model.Label;
import net.begincode.core.model.LabelExample;
import net.begincode.core.model.ProblemLabelExample;
import net.begincode.core.param.LabelAndProblemId;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Stay on 2016/8/26  19:40.
 */
@Service
public class LabelService {

    @Resource
    private LabelMapper labelMapper;

    @Resource
    private BizLabelMapper bizLabelMapper;

    /**
     * 新增标签
     *
     * @param label
     */
    public void createLabel(Label label) {
        labelMapper.insertSelective(label);
    }

    /**
     * 根据标识查找标签
     *
     * @param id
     * @return
     */
    public Label selectById(Integer id) {
        return labelMapper.selectByPrimaryKey(id);
    }


    /**
     * 传入标签id集合 用数据库in查询
     *
     * @param listId
     * @return
     */
    public List<Label> selectInById(List<Integer> listId){
        LabelExample labelExample = new LabelExample();
        LabelExample.Criteria criteria = labelExample.createCriteria();
        criteria.andLabelIdIn(listId).andDeleteFlagEqualTo(Integer.parseInt(DeleteFlagEnum.NO_DEL_FLAG.getCode()));
        return labelMapper.selectByExample(labelExample);
    }



    /**
     * 根据标签名查找标签
     *
     * @param labelName 传入的标签名
     * @return 返回存在的标签
     */
    public Label selectByName(String labelName) {
        LabelExample labelExample = new LabelExample();
        LabelExample.Criteria criteria = labelExample.createCriteria();
        criteria.andLabelNameEqualTo(labelName);
        List<Label> list = labelMapper.selectByExample(labelExample);
        if (list.size() > 0 && list != null) {
            return list.get(0);
        }
        return null;
    }

    /**
     * @return 查询所有label标签
     */
    public List<Label> selectAll() {
        LabelExample example = new LabelExample();
        return labelMapper.selectByExample(example);
    }


    /**
     * 查询所有标签
     * @return
     */
    public List<Label> selectAllLabel() {

        return bizLabelMapper.selectHotLabel();
    }

    public List<Label> selectLabelByProblemId(Integer problemId){
        return bizLabelMapper.selectLabelByProblemId(problemId);
    }


    /**
     * 查询标签名字
     * @return
     */
	public String selectLabelById(Integer labelId) {
		return labelMapper.selectByPrimaryKey(labelId).getLabelName();
	}
	
	/**
	 * 如果为Null 返回所有P_L表的数据
	 * @param 一组problemId
	 * @return	返回problemId和对应lable的类列表 
	 */
	public List<LabelAndProblemId> selectLabelAndProblemIdByProblemId(List<Integer> problemIds){
		return bizLabelMapper.selectLabelAndProblemIdByProblemId(problemIds);
	}
}
