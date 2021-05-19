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
public class StudyGroupControllerTests {
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;

	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	/*@Test
	public void groupCreateGetTest() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/group/create")).andReturn()
		.getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void groupCreatePostTest() throws Exception {
		
		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "study0516";
			}
		};
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/group/create")
				.param("g_name", "studyam1009")
				.param("g_content", "studyam1009")
				.param("g_secreat", "secreat")
				.param("g_passwd", "studyam1009")
				.param("g_category", "study")
				.param("g_memnum", "10")
				.principal(principal))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/
	
	// group main 테스트 필요
	
	/*@Test
	public void groupDetailRead() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/group/read?g_num=gn1")).andReturn()
				.getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void groupUpdateRead() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/group/update?g_num=gn1")).andReturn()
				.getModelAndView().getModelMap());
	}*/
	
	/*@Test
	public void groupUpdate() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/group/update")
				.param("g_num", "gn1")
				.param("g_name", "Unity, C#")
				.param("g_content", "Unity Study")
				.param("g_secreat", "secreat")
				.param("g_passwd", "studyam1009")
				.param("g_category", "study")
				.param("g_memnum", "10"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage); 
	}*/
	
	@Test
	public void groupRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/group/remove")
				.param("g_num", "gn1")
				.param("g_name", "Unity, C#")
				.param("g_content", "Unity Study")
				.param("g_secreat", "secreat")
				.param("g_passwd", "studyam1009")
				.param("g_category", "study")
				.param("g_memnum", "10"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
}
