package net.begincode.core.handler;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.model.Label;
import net.begincode.core.service.LabelService;

import org.springframework.stereotype.Component;
/**
 * 标签事务层
 * @author Administrator
 * @date 2016年9月26日
 */
@Component
public class LabelHandler {

	@Resource
    private LabelService labelService;
	
	public List<Label> getAllLabel(){
		return labelService.selectAllLabel();
	}
}
