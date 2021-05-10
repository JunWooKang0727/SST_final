package org.sst.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.sst.domain.ReportCardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReportCardServiceTests {
	@Setter(onMethod_ = { @Autowired })
	ReportCardService service;
	
//	@Test
//	public void testList(){
//		log.info(service.listReportCard("ggy"));
//	}
	
//	@Test
//	public void testRead() {
//		log.info(service.readReportCard("rc22"));
//	}
	
//	@Test
//	public void testCreate(){
//		ReportCardVO vo = new ReportCardVO();
//		vo.setM_id("ggy");
//		vo.setRc_subtype("ServiceTest");
//		vo.setRc_type("Service Test");
//		vo.setRc_title("ServiceTest");
//		
//		log.info(service.createReportCard(vo));
//	}
	
//	@Test
//	public void testDelete(){
//		log.info(service.deleteReportCard("rc41"));
//	}
}
