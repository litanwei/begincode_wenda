package net.begincode.mockTest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import net.begincode.controller.MessageController;

public class MessageMockTest extends BaseTest {
	private Cookie[] cookies;
	private String url;

	@Before
	public void init() {
		cookies = getCookies();
	}

	// 这里定义自己的Cookie 用于@AuthPassport验证和Request获取User
	public Cookie[] getCookies() {
		Cookie accessToken = new Cookie("accessToken", "631868D1C8BC1E70F0FB22A92A67342F");
		Cookie openId = new Cookie("openId", "160E4C8F067402115532775DD0437747");
		Cookie check = new Cookie("check", "0");
		Cookie[] cookies = { accessToken, openId, check };
		return cookies;
	}

	@Test
	public void getPageTest() throws Exception {
		url="/message/messageremind.htm";
		MvcResult mvcResult = mockMvc
				// 定制request
				.perform(post(url)// 定义Url和request参数
						.cookie(cookies) // Cookie
						.param("nowpage", "1")// 参数 可以多个param
						.accept(MediaType.TEXT_HTML)) // 确定Type
				// ------断言-----------
				.andExpect(content().contentType("text/html;charset=UTF-8")) // 返回值属性
				.andExpect(status().isOk()) // 成功
				.andExpect(handler().handlerType(MessageController.class))// Controller类
				.andExpect(handler().methodName("getPage"))// Controller方法
				.andReturn();// 放回值
		JSONObject j = new JSONObject(mvcResult.getResponse().getContentAsString()); // 获取response内容转Json
		// asser断言
		assertEquals(j.get("code"), "0");
	}

	@Test
	public void monitoringMessageClickTest() throws Exception {
		url="/message/19.htm";
		mockMvc.perform(post(url).cookie(cookies).accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(handler().handlerType(MessageController.class))
				.andExpect(handler().methodName("monitoringMessageClick")).andReturn();
	}

	@Test
	public void countMessageTest() throws Exception {
		url="/message/pagesize.htm";
		MvcResult mvcResult = mockMvc.perform(post(url).cookie(cookies).accept(MediaType.TEXT_HTML))
				.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(handler().handlerType(MessageController.class))
				.andExpect(handler().methodName("countMessage")).andReturn();
		JSONObject j = new JSONObject(mvcResult.getResponse().getContentAsString());
		assertEquals(j.get("code"), "0");
	}
}
