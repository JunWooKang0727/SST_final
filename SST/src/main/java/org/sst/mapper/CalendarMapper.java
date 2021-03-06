package org.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sst.domain.CalendarTodoVO;

public interface CalendarMapper {
	public int insertTodo(CalendarTodoVO todo);
	public List<CalendarTodoVO> listTodo(String id);
	public int updateCheckTodo(CalendarTodoVO todo);
	public int updateNonCheckTodo(CalendarTodoVO todo);
	public int deleteTodo(CalendarTodoVO todo);
}
