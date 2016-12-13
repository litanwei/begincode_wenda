import net.begincode.controller.AnsAgreeController;
import net.begincode.controller.UserController;
import net.begincode.core.handler.AnsAgreeHandler;
import net.begincode.core.model.AnsAgree;
import net.begincode.core.model.BegincodeUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/12/13  16:21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/dataSource.xml", "classpath*:spring/applicationContext-core.xml","classpath*:mybatis.xml"})
public class AnsAgreeTest {

    @Resource
    private AnsAgreeHandler ansAgreeHandler;

    // 模拟request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Test
    public void testHandler(){
        AnsAgree ansAgree = new AnsAgree();
        ansAgree.setAnswerId(1);
        ansAgree.setAgree(1);
        ansAgree.setBegincodeUserId(1);
        ansAgreeHandler.updateAnswerAgrCountAndAnsAgree(ansAgree);
    }
}
