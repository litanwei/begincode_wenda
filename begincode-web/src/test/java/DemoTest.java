import net.begincode.controller.UserController;
import net.begincode.core.handler.AnsAgreeHandler;
import net.begincode.core.handler.AnswerHandler;
import net.begincode.core.handler.DemoHandler;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.Demo;
import net.begincode.core.service.BegincodeUserService;
import net.begincode.core.service.ProblemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
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
    private AnswerHandler answerHandler;
    @Resource
    private DemoHandler demoHandler;
    @Resource
    private UserHandler userHandler;
    @Resource
    private BegincodeUserService begincodeUserService;
    @Resource
    private ProblemService problemService;

    // 模拟request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    // 注入loginController
    @Autowired
    private UserController userController ;

    // 执行测试方法之前初始化模拟request,response


    @Resource
    private AnsAgreeHandler ansAgreeHandler;

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
