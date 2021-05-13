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
import org.springframework.web.filter.CharacterEncodingFilter;

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
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
	}
	
//	@Test
//	public void testList() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/reportcard/list/ggy"))
//				.andReturn().getModelAndView().getModelMap());
//	}
//	@Test
//	public void testRead() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/reportcard/read/rc4"))
//				.andReturn().getModelAndView().getModelMap());
//	}
//	@Test
//	public void testCreate() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/reportcard/create")
//				.param("rc_type", "Controller Test")
//				.param("m_id", "ggy")
//				.param("rc_title", "Controller Test")
//				.param("rc_subtype", "학교성적")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
//	@Test
//	public void testDelete() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.delete("/reportcard/delete/rc49/학교성적/ggy")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
	
	
	
//	@Test
//	public void testCreate() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/reportcard/schooltest/create")
//				.param("rc_num", "rc49")
//				.param("st_date", "2019-08-07")
//				.param("st_year", "1")
//				.param("st_semester", "1")
//				.param("st_test", "Controller Test")
//				.param("scorelist[0].ss_name", "Controller Test")
//				.param("scorelist[0].ss_score", "88")
//				.param("scorelist[1].ss_name", "Controller Test")
//				.param("scorelist[1].ss_score", "38")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
//	@Test
//	public void testRead() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/reportcard/schooltest/read/st47"))
//				.andReturn().getModelAndView().getModelMap());
//	}
//	@Test
//	public void testDelete() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.delete("/reportcard/schooltest/delete/rc22/st47")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
//	@Test
//	public void testUpdate() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/reportcard/schooltest/update")
//				.param("st_num", "st48")
//				.param("rc_num", "rc22")
//				.param("st_date", "2019-08-07")
//				.param("st_year", "1")
//				.param("st_semester", "1")
//				.param("st_test", "Update Test")
//				
//				.param("scorelist[0].ss_num", "ss69")
//				.param("scorelist[0].ss_name", "Update Test")
//				.param("scorelist[0].ss_score", "100")
//				
//				.param("scorelist[1].ss_num", "ss70")
//				.param("scorelist[1].ss_name", "Update Test")
//				.param("scorelist[1].ss_score", "100")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
	
	
//	@Test
//	public void testCreate() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/reportcard/schoolscore/create")
//				.param("st_num", "st48")
//				.param("ss_name", "Controller Test3")
//				.param("ss_score", "88")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
	@Test
	public void testDelete() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.delete("/reportcard/schoolscore/delete/ss75/st48")
				).andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
}
