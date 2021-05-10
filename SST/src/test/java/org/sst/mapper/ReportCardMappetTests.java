package org.sst.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.ReportCardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReportCardMappetTests {
	@Setter(onMethod_=@Autowired)
	private ReportCardMapper mapper;
	
//	@Test
//	public void testList(){
//		log.info(mapper.listReportCard("ggy"));
//	}
	
//	@Test
//	public void testRead() {
//		log.info(mapper.readReportCard("rc22"));
//	}
	
//	@Test
//	public void testCreate(){
//		ReportCardVO vo = new ReportCardVO();
//		vo.setM_id("ggy");
//		vo.setRc_subtype("ServiceTest");
//		vo.setRc_type("Service Test");
//		vo.setRc_title("ServiceTest");
//		
//		log.info(mapper.createReportCard(vo));
//	}
	
//	@Test
//	public void testDelete(){
//		log.info(mapper.deleteReportCard("rc43"));
//	}
}
