package org.sst.controller;

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

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class ReportCardControllerTests {
	@Setter(onMethod_={@Autowired})
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
//	public void testList() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/reportcard/list/ggy"))
//				.andReturn().getModelAndView().getModelMap());
//	}
	
//	@Test
//	public void testRead() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/reportcard/read/rc22"))
//				.andReturn().getModelAndView().getModelMap());
//	}
	
//	@Test
//	public void testRegister() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/reportcard/create")
//				.param("rc_type", "Controller Test")
//				.param("m_id", "ggy")
//				.param("rc_title", "Controller Test")
//				.param("rc_subtype", "Controller Test")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
	
//	@Test
//	public void testDelete() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.delete("/reportcard/delete/rc48/ggy")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
	
}
