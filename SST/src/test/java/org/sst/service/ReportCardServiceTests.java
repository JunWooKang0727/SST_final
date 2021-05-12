package org.sst.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.sst.domain.ReportCardVO;
import org.sst.domain.SchoolScoreVO;
import org.sst.domain.SchoolTestVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
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
	
	

//	@Test
//	public void testList(){
//		log.info(service.listSchoolTest("rc4"));
//	}
//	@Test
//	public void testRead() {
//		log.info(service.readSchoolTest("st7"));
//	}
//	@Test
//	public void testCreate(){
//		SchoolTestVO vo = new SchoolTestVO();
//		vo.setRc_num("rc4");
//		vo.setSt_date("2019-08-07");
//		vo.setSt_year(3);
//		vo.setSt_semester(4);
//		vo.setSt_test("Service Test");
//		
//		log.info("결과!:      "+service.createSchoolTest(vo));
//	}
//	@Test
//	public void testDelete(){
//		log.info("결과!!!!:     "+service.deleteSchoolTest("st43"));
//	}
//	@Test
//	public void testUpdate(){
//		SchoolTestVO vo = new SchoolTestVO();
//		vo.setRc_num("rc4");
//		vo.setSt_date("2019-08-07");
//		vo.setSt_year(3);
//		vo.setSt_semester(4);
//		vo.setSt_test("Service Update2");
//		vo.setSt_num("st44");
//		log.info("결과!!!!:     "+service.updateSchoolTest(vo));
//	}
	
	
	
//	@Test
//	public void testList(){
//		log.info(service.listSchoolScore("st4"));
//	}
//	@Test
//	public void testCreate(){
//		SchoolScoreVO vo = new SchoolScoreVO();
//		vo.setSt_num("st42");
//		vo.setSs_name("ServiceTest2");
//		vo.setSs_score(66);
//		
//		log.info("결과!:      "+service.createSchoolScore(vo));
//	}
//	@Test
//	public void testDelete(){
//		log.info("결과!!!!:     "+service.deleteSchoolScore("ss62"));
//	}
//	@Test
//	public void testDelete2(){
//		log.info("결과!!!!:     "+service.deleteSchoolScore("ss62"));
//	}
//	@Test
//	public void testUpdate(){
//		SchoolScoreVO vo = new SchoolScoreVO();
//		vo.setSt_num("st42");
//		vo.setSs_name("ServiceTestUpdate");
//		vo.setSs_score(66);
//		vo.setSs_num("ss66");
//		log.info("결과!!!!:     "+service.updateSchoolScore(vo));
//	}
	
//	
//	@Test
//	public void testList(){
//		log.info(service.listLicenseTest("rc22"));
//	}
//	@Test
//	public void testRead() {
//		log.info(service.readLicenseTest("lt21"));
//	}
	@Test
	public void testCreate(){
		SchoolTestVO vo = new SchoolTestVO();
		vo.setRc_num("rc4");
		vo.setSt_date("2019-08-07");
		vo.setSt_year(3);
		vo.setSt_semester(4);
		vo.setSt_test("Service Test");
		
		log.info("결과!:      "+service.createSchoolTest(vo));
	}
//	@Test
//	public void testDelete(){
//		log.info("결과!!!!:     "+service.deleteSchoolTest("st43"));
//	}
//	@Test
//	public void testUpdate(){
//		SchoolTestVO vo = new SchoolTestVO();
//		vo.setRc_num("rc4");
//		vo.setSt_date("2019-08-07");
//		vo.setSt_year(3);
//		vo.setSt_semester(4);
//		vo.setSt_test("Service Update2");
//		vo.setSt_num("st44");
//		log.info("결과!!!!:     "+service.updateSchoolTest(vo));
//	}
}
