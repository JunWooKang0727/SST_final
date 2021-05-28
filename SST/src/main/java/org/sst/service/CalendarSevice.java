package org.sst.service;

import java.util.List;

import org.sst.domain.CalendarTodoVO;

public interface CalendarSevice {
	public int register(CalendarTodoVO vo, String id);
	public List<CalendarTodoVO> list(String id);
	public int updateCheck(CalendarTodoVO vo, String id);
	public int updateNonCheck(CalendarTodoVO vo, String id);
	public int delete(CalendarTodoVO vo, String id);
}
