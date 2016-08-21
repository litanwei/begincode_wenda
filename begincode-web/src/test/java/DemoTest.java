import net.begincode.core.handler.DemoHandler;
import net.begincode.core.model.Demo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangsj on 2016/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/dataSource.xml", "classpath*:spring/applicationContext-core.xml","classpath*:mybatis.xml"})
public class DemoTest  extends AbstractJUnit4SpringContextTests {
    @Resource
    private DemoHandler demoHandler;

    @Test
    public void addDemoTest(){
        Demo demo = new Demo();
        demo.setUsername("test");
        demo.setPassword("password");
        demoHandler.addDemo(demo);
        Assert.assertTrue(demo.getId() > 0);
    }
    @Test
    public void selDemoTest(){
        List<Demo> demoList = demoHandler.selectAll();
        Assert.assertTrue(demoList.size() > 0);

    }
}
