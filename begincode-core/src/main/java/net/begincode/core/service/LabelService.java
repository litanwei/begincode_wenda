package net.begincode.core.service;

import net.begincode.core.mapper.LabelMapper;
import net.begincode.core.model.Label;
import net.begincode.core.model.LabelExample;
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

    /**
     * 新增标签
     * @param label
     */
    public void createLabel(Label label)
    {
        labelMapper.insertSelective(label);
    }


    /**
     * 根据标签名查找标签
     * @param labelName  传入的标签名
     * @return 返回存在的标签
     */
    public Label selectByName(String labelName)
    {
        LabelExample labelExample = new LabelExample();
        LabelExample.Criteria criteria = labelExample.createCriteria();
        criteria.andLabelNameEqualTo(labelName);
        List<Label> list = labelMapper.selectByExample(labelExample);
        if(list.size()>0 && list!=null)
        {
            return list.get(0);
        }
        return null;
    }




    /**
     *
     * @return 查询所有label标签
     */
    public List<Label> selectAll()
    {
        LabelExample example = new LabelExample();
        return labelMapper.selectByExample(example);
    }

}
