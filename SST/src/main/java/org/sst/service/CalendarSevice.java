package org.sst.service;

import java.util.List;

import org.sst.domain.CalendarTodoVO;

public interface CalendarSevice {
	public int register(CalendarTodoVO vo);
	public List<CalendarTodoVO> list();
	public int updateCheck(CalendarTodoVO vo);
	public int updateNonCheck(CalendarTodoVO vo);
	public int delete(CalendarTodoVO vo);
}
