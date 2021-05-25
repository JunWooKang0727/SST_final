package org.sst.controller;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.sst.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class MemberControllerTests {
	// 스프링 컨테이너 
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	/*@Test
	public void testMain() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/member/main")).andReturn()
				.getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void testLogout() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/member/logout")).andReturn()
				.getModelAndView().getModelMap());
	}*/
	
	// GET /member/create
	/*@Test
	public void testSignUpget() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/member/create")).andReturn()
		.getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void testSignUp() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/member/create")
				.param("m_id", "hel8888")
				.param("m_pw", "hello0511")
				.param("m_name", "hello0511")
				.param("m_phone", "hello0511")
				.param("m_birth", "hello0511")
				.param("m_email", "hello0511@ga.com")
				.param("enabled", "1")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/
	
	/*@Test
	public void testSignUpFin() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/member/joinFin")).andReturn()
		.getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void testCheckId() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/member/create")
				.param("id", "hello0511")
				).andReturn().getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void testRead() throws Exception {
		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "hello0599";
			}
		};
		
		log.info("결과 : " + mockMvc.perform(MockMvcRequestBuilders.get("/member/read")
				.principal(principal))
				.andReturn().getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void testUpdate() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/member/update")
				.param("m_id", "hello0599")
				.param("m_name", "hello0599")
				.param("m_phone", "hello0599")
				.param("m_birth", "hello0599")
				.param("m_email", "hello0599@ga.com")
				).andReturn().getModelAndView().getViewName();
		
		log.info("결과 : " + resultPage);
	}*/
	
	/*@Test
	public void testDelete() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/member/delete")).andReturn()
		.getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void testDeletePost() throws Exception {
		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "study0516";
			}
		};
		
		log.info("결과 : " + mockMvc.perform(MockMvcRequestBuilders.post("/member/delete")
				.param("password", "study0516")
				.principal(principal))
				.andReturn().getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void testDelete() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/member/login")).andReturn()
		.getModelAndView().getModelMap());
	}*/
}
