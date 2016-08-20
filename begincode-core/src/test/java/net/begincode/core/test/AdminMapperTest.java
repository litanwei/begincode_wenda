package net.begincode.core.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.begincode.core.mapper.AdminMapper;
import net.begincode.core.model.Admin;


/**
 * @ClassName:AdminMapperTest.java
 * @Description:maven install测试用例
 * @author Stay
 * @date 2016年8月20日下午2:40:08
 */
public class AdminMapperTest {

	private Logger logger =  LoggerFactory.getLogger(AdminMapperTest.class);
	private static ClassPathXmlApplicationContext ctx;
	private static AdminMapper adminMapper;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext-core.xml");
		adminMapper = (AdminMapper) ctx.getBean("adminMapper");
	}
	
	@Test
	public void testCreateAdmin()
	{
		Admin admin = new Admin();
		admin.setUsername("stay");
		admin.setPassword("stay");
		adminMapper.insert(admin);
	}
	
	@Test
	public void testSelectAdminById()
	{
		Admin ad = adminMapper.selectByPrimaryKey(1);
		logger.info(ad.getId()+","+ad.getUsername()+" "+ad.getPassword());
	}
	
	
}
