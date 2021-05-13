package org.sst.mapper;

import java.util.Date;
import java.util.List;

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
public class CalendarTodoTests {
	@Setter(onMethod_ = @Autowired)
	private CalendarMapper mapper;

	/*@Test
	public void testInsert() {
		CalendarTodoVO vo = new CalendarTodoVO();
		vo.setT_category("1");
		vo.setT_contents("1");
		vo.setT_enddate(new Date(2020, 9, 10));
		vo.setT_startdate(new Date(2020, 9, 10));
		vo.setT_num("t32");
		vo.setT_title("1");
		vo.setT_todocheck(0);

		mapper.insertTodo(vo);
	}*/

	/*
	 * @Test public void testList(){
	 * 
	 * List<CalendarTodoVO> list = mapper.listTodo();
	 * 
	 * list.forEach(tno -> log.info(tno));
	 * 
	 * }
	 */
	/*
	 * @Test public void testUpdate() {
	 * 
	 * CalendarTodoVO vo = new CalendarTodoVO();
	 * 
	 * vo.setT_num("t33");
	 * 
	 * log.info(mapper.updateCheckTodo(vo)); }
	 */
	/*
	 * @Test public void testUpdate2() {
	 * 
	 * CalendarTodoVO vo = new CalendarTodoVO();
	 * 
	 * vo.setT_num("t33");
	 * 
	 * log.info(mapper.updateNonCheckTodo(vo)); }
	 */
	@Test
	public void testDelete() {
		CalendarTodoVO vo = new CalendarTodoVO();

		vo.setT_num("t33");

		log.info(mapper.deleteTodo(vo));
	}
}
