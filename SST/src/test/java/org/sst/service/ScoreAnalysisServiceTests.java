package org.sst.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ScoreAnalysisServiceTests {
	@Setter(onMethod_ = { @Autowired })
	private ScoreAnalysisService service;
	
//	@Test
//	public void testScore(){
//		log.info(service.allSubjectScoreSchoolTest("rc4"));
//	}
	
	@Test
	public void testAvg(){
		log.info(service.averageSchoolTest("rc4"));
	}

}
