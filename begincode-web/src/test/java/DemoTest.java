import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.begincode.controller.UserController;
import net.begincode.core.handler.DemoHandler;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.Demo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yangsj on 2016/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/dataSource.xml", "classpath*:spring/applicationContext-core.xml","classpath*:mybatis.xml"})
public class DemoTest  extends AbstractJUnit4SpringContextTests {
    @Resource
    private DemoHandler demoHandler;
    @Resource
    private UserHandler userHandler;
    // 模拟request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    // 注入loginController
    @Autowired
    private UserController userController ;

    // 执行测试方法之前初始化模拟request,response


    @Test
    public void testCreateUser(){
        BegincodeUser user = new BegincodeUser();
        user.setAccessToken("2");
        user.setOpenId("2");
        user.setCheckFlag("2");
        user.setBegincodeUserId(2);
        user.setNickname("2");
        BegincodeUser begincodeUser = userHandler.createUser(user);
        Assert.assertTrue(begincodeUser != null);

    }
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

    /**
     *
     * @Title：testFindOrCreateUser
     * @Description: 测试用户登录
     */
    @Test
    public void testLogin() {
        response = new MockHttpServletResponse();
        BegincodeUser user;
        try {
            user = new BegincodeUser();
            user.setAccessToken("3");
            user.setOpenId("3");
            user.setCheckFlag("3");
            user.setBegincodeUserId(3);
            user.setNickname("3");
            userController.findOrCreateUser(response,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
