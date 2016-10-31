import net.begincode.controller.UserController;
import net.begincode.core.handler.AnswerHandler;
import net.begincode.core.handler.DemoHandler;
import net.begincode.core.handler.MessageHandler;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.mapper.BizProblemMapper;
import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.*;
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
import java.util.ArrayList;
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


    @Test
    public void selAnswer(){
        Answer answer = new Answer();
        answer.setProblemId(1);
        List<Answer> answers = answerHandler.selAllAnswerByExample(answer);
        for(Answer answer1:answers){
            System.out.println(answer1.getUserName());
        }
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
     @Resource
    private MessageHandler messageHandler;

    @Test
    public void testaa(){
        List<Problem> list = new ArrayList<>();
        Problem p = new Problem();
        p.setProblemId(6);
        list.add(p);
        Problem pa = new Problem();
        pa.setProblemId(6);
        list.add(pa);
        System.out.print(list.size());
        problemService.batchUpdateView(list);
    }

}
