package org.sst.mapper;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.LicenseScoreVO;
import org.sst.domain.LicenseTestVO;
import org.sst.domain.ReportCardVO;
import org.sst.domain.SchoolScoreVO;
import org.sst.domain.SchoolTestVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReportCardMapperTests {
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
	
	
	
//	@Test
//	public void testList(){
//		log.info(mapper.listSchoolTest("rc4"));
//	}
//	@Test
//	public void testRead() {
//		log.info(mapper.readSchoolTest("st4"));
//	}
//	@Test
//	public void testCreate(){
//		SchoolTestVO vo = new SchoolTestVO();
//		vo.setRc_num("rc4");
//		vo.setSt_date("2019-08-07");
//		vo.setSt_year(3);
//		vo.setSt_semester(4);
//		vo.setSt_test("mapper Test");
//		log.info("결과!:          "+mapper.createSchoolTest(vo));
//	}
//	@Test
//	public void testUpdate(){
//		SchoolTestVO vo = new SchoolTestVO();
//		vo.setSt_num("st42");
//		vo.setRc_num("rc4");
//		vo.setSt_date("2021-08-07");
//		vo.setSt_year(5);
//		vo.setSt_semester(6);
//		vo.setSt_test("mapper Update Test");
//		log.info("결과!:          "+mapper.updateSchoolTest(vo));
//	}
//	@Test
//	public void testDelete(){
//		log.info("결과!:                 "+mapper.deleteSchoolTest("st41"));
//	}
	
	
//	@Test
//	public void testList(){
//		log.info(mapper.listSchoolScore("st7"));
//	}
//	@Test
//	public void testCreate(){
//		SchoolScoreVO vo = new SchoolScoreVO();
//		vo.setSt_num("st42");
//		vo.setSs_name("MapperTest");
//		vo.setSs_score(66);
//		log.info("결과!:          "+mapper.createSchoolScore(vo));
//	}
//	@Test
//	public void testUpdate(){
//		SchoolScoreVO vo = new SchoolScoreVO();
//		vo.setSt_num("st42");
//		vo.setSs_name("MapperTestUpdate");
//		vo.setSs_score(66);
//		vo.setSs_num("ss61");
//		log.info("결과!:          "+mapper.updateSchoolScore(vo));
//	}
//	@Test
//	public void testDelete(){
//		log.info("결과!:                 "+mapper.deleteSchoolScore("ss61"));
//	}
//	@Test
//	public void testDeleteSchoolTestScore(){
//		log.info("결과!:                 "+mapper.deleteSchoolTestScore("st42"));
//	}
	
	
//	@Test
//	public void testList(){
//		log.info(mapper.listLicenseTest("rc5"));
//	}
//	@Test
//	public void testRead() {
//		log.info(mapper.readLicenseTest("lt1"));
//	}
//	@Test
//	public void testCreate(){
//		LicenseTestVO vo = new LicenseTestVO();
//		vo.setRc_num("rc5");
//		vo.setLt_date("2019-08-07");
//		vo.setLt_round(3);
//		vo.setLt_test("mapper Test");
//		log.info("결과!:          "+mapper.createLicenseTest(vo));
//	}
//	@Test
//	public void testUpdate(){
//		LicenseTestVO vo = new LicenseTestVO();
//		vo.setLt_num("lt42");
//		vo.setRc_num("rc5");
//		vo.setLt_date("2019-08-07");
//		vo.setLt_round(3);
//		vo.setLt_test("mapper Test update");
//		log.info("결과!:          "+mapper.updateLicenseTest(vo));
//	}
//	@Test
//	public void testDelete(){
//		log.info("결과!:                 "+mapper.deleteLicenseTest("lt41"));
//	}
	
	
//	@Test
//	public void testList(){
//		log.info(mapper.listLicenseScore("lt1"));
//	}
//	@Test
//	public void testCreate(){
//		LicenseScoreVO vo = new LicenseScoreVO();
//		vo.setLt_num("lt42");
//		vo.setLs_name("MapperTest");
//		vo.setLs_score(66);
//		vo.setLs_goal(66);
//		log.info("결과!:          "+mapper.createLicenseScore(vo));
//	}
//	@Test
//	public void testUpdate(){
//		LicenseScoreVO vo = new LicenseScoreVO();
//		vo.setLs_num("ls41");
//		vo.setLt_num("lt1");
//		vo.setLs_name("MapperTestUpdate");
//		vo.setLs_score(66);
//		vo.setLs_goal(66);
//		log.info("결과!:          "+mapper.updateLicenseScore(vo));
//	}
//	@Test
//	public void testDelete(){
//		log.info("결과!:                 "+mapper.deleteLicenseScore("ls41"));
//	}
//	@Test
//	public void testDeleteLicenseTestScore(){
//		log.info("결과!:                 "+mapper.deleteLicenseTestScore("lt42"));
//	}
	
	
	
//	@Test
//	public void testAnalysis1(){
//		log.info(mapper.selectReportCardSubject("rc4"));
//	}
	
	@Test
	public void testAnalysis2(){
		HashMap map = new HashMap();
		map.put("rc_num", "rc5");
		map.put("list", mapper.schoolTestSubjects("rc5"));
		log.info(mapper.allSubjectScoreSchoolTest(map));
	}
//	@Test
//	public void testAnalysis3(){
//		log.info(mapper.averageSchoolTest("rc4"));
//	}

//	@Test
//	public void testAnalysis4(){
//		HashMap map = new HashMap();
//		map.put("rc_num", "rc22");
//		map.put("list", mapper.licenseTestSubjects("rc22"));
//		log.info(mapper.allSubjectScoreLicenseTest(map));
//	}
	
//	@Test
//	public void testRecommend(){
//		ReportCardVO vo = new ReportCardVO();
//		vo.setRc_subtype("정보처리기사");
//		vo.setRc_type("License");
//		log.info(mapper.recommendLicenseTest(vo));
//	}
	
//	@Test
//	public void testWortSubject(){
//		HashMap map = mapper.worstSubject("rc2");
//		map.put("rc_type", "고등학교");
//		log.info(map);
//		log.info(mapper.recommendSchoolTest(map));
//		
//	}
}
