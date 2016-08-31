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
        labelService.createLabel(label);
    }


    /**
     * 用标签名查看是否存在相同数据
     * @param labelName 传入的标签名
     * @return
     */
    public Label selectByLabelName(String labelName)
    {
        Label label = labelService.selectByName(labelName);
        return label;
    }


}
