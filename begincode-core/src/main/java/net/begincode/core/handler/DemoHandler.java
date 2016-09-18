package net.begincode.core.handler;

import net.begincode.core.model.Demo;
import net.begincode.core.service.DemoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangsj on 2016/8/20.
 * Demo handler
 */
@Component
public class DemoHandler {
    @Resource
    private DemoService demoService;

    /**
     * 新增Demo
     * @param demo
     */
    public void addDemo(Demo demo){
        demoService.addDemo(demo);
    }

    /**
     * Id修改修改Demo
     * @parm demo
     */
    public void updateDemoById(Demo demo){
        demoService.updateDemoById(demo);
    }

    /**
     * 根据标识删除Demo
     * @param id  demo标识
     */
    public void delDemo(Integer id){
        demoService.delDemo(id);
    }

    /**
     * @return 查询所有demo列表
     */
    public List<Demo> selectAll(){
        return demoService.selectAll();
    }

    /**
     * 根据标识查询Demo
     * @param id
     * @return
     */
    public Demo selectById(Integer id){
        return demoService.selectById(id);
    }
}
