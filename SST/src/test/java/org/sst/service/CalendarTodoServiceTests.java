package org.sst.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.CalendarTodoVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class CalendarTodoServiceTests {
	@Setter(onMethod_ = @Autowired)
	private CalendarSevice service;

	/*@Test
	public void testInsert() {

		CalendarTodoVO vo = new CalendarTodoVO();

		vo.setT_category("1");
		vo.setT_contents("1");
		vo.setT_enddate(new Date(2020, 9, 10));
		vo.setT_startdate(new Date(2020, 9, 10));
		vo.setT_title("1");
		
		service.register(vo);

	}*/

	/*@Test
	public void testList(){
	
	service.list().forEach(tdno->log.info(tdno));
	
	}*/

	/*@Test
	public void testUpdate1(){
		CalendarTodoVO vo = new CalendarTodoVO();
		vo.setT_num("t34");
		log.info(service.updateCheck(vo));
	}*/

	/*@Test
	public void testUpdate2(){
		CalendarTodoVO vo = new CalendarTodoVO();
		vo.setT_num("t34");
		log.info(service.updateNonCheck(vo));
	}
*/
	@Test
	public void testDelete(){
		CalendarTodoVO vo = new CalendarTodoVO();
		vo.setT_num("t34");
		log.info("delete Row : "+service.delete(vo));
	
	}
}
