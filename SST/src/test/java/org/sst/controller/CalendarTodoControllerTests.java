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
public class CalendarTodoControllerTests {
	@Setter(onMethod_=@Autowired)
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	/*@Test
	public void textList() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/fullcalendar-5.6.0/Calendar/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}*/
	
	/*@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/fullcalendar-5.6.0/Calendar/create")
				.param("t_title", "다시")
				.param("t_contents", "한번더")
				.param("t_writer", "강준우")
				.param("t_category", "강택수")
				.param("t_startdate", "2020-09-09")
				.param("t_enddate", "2020-09-09")
				.param("t_todocheck", "1")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/
	
	
	/*@Test
	public void testUpdate() throws Exception{
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/fullcalendar-5.6.0/Calendar/check")
						.param("t_num", "t35"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}*/
	/*@Test
	public void testUpdate2() throws Exception{
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/fullcalendar-5.6.0/Calendar/noncheck")
						.param("t_num", "t35"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}*/
	@Test
	public void testDelete() throws Exception{
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.delete("/fullcalendar-5.6.0/Calendar/delete")
						.param("t_num", "t35"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
}
