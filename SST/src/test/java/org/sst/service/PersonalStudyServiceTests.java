package org.sst.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.PersonalStudyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class PersonalStudyServiceTests {
	@Setter(onMethod_=@Autowired)
	private PersonalStudyService service;
	
	/*@Test
	public void testInsert() {

		PersonalStudyVO vo = new PersonalStudyVO();

		vo.setPs_num("ps12");
		vo.setPs_category("์์ด");
		vo.setPs_time("00:00:00:00");
		vo.setPs_place("์นดํ");
		vo.setPs_starttime(new Date(2020, 9, 10));
		vo.setM_id("1234");
		service.register(vo);
	}*/
}
