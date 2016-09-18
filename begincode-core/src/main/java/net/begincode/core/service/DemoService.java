package net.begincode.core.service;

import net.begincode.core.mapper.DemoMapper;
import net.begincode.core.model.Demo;
import net.begincode.core.model.DemoExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangsj on 2016/8/20.
 */
@Service
public class DemoService {
    @Resource
    private DemoMapper demoMapper;

    /**
     * 新增Demo
     * @param demo
     */
    public void addDemo(Demo demo){
        demoMapper.insertSelective(demo);
    }

    /**
     * Id修改修改Demo
     * @parm demo
     */
    public void updateDemoById(Demo demo){
        demoMapper.updateByPrimaryKey(demo);
    }

    /**
     * 根据标识删除Demo
     * @param id  demo标识
     */
    public void delDemo(Integer id){
        demoMapper.deleteByPrimaryKey(id);
    }

    /**
     * @return 查询所有demo列表
     */
    public List<Demo> selectAll(){
        DemoExample demoExample = new DemoExample();
        return demoMapper.selectByExample(demoExample);
    }

    /**
     * 根据标识查询Demo
     * @param id
     * @return
     */
    public Demo selectById(Integer id){
        return demoMapper.selectByPrimaryKey(id);
    }
}
