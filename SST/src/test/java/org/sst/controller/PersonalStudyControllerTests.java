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
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class PersonalStudyControllerTests {
	@Setter(onMethod_=@Autowired)
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/personalstudy/create")
				.param("ps_num", "ps_10")
				.param("ps_category", "스프링")
				.param("ps_time", "00:00:00:00")
				.param("ps_place", "오늘의집")
				.param("ps_starttime", "2020-09-09")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
}






