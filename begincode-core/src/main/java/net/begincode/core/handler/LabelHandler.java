package net.begincode.core.handler;

import net.begincode.core.model.Label;
import net.begincode.core.service.LabelService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/8/27  22:25.
 */
@Component
public class LabelHandler {
    @Resource
    private LabelService labelService;

    /**
     * 新增标签
     * @param label
     */
    public void addLabel(Label label)
    {
        labelService.addLabel(label);
    }


    /**
     * 根据标签名查找标签
     * @param labelName  传入的标签名
     * @return 如果存在返回true  不存在返回 false
     */
    public boolean selectByLabelName(String labelName)
    {
        Label label = labelService.selectByName(labelName);
        if(label == null)
        {
            return false;
        }
        return  true;
    }


}
