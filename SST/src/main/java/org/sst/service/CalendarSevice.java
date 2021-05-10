package org.sst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sst.domain.CalendarTodoVO;

public interface CalendarSevice {
	public int insertTodoService(HttpServletRequest request) throws Exception;
	public List<CalendarTodoVO> listTodo()throws Exception;
	public int updateCheckService(HttpServletRequest request)throws Exception;
	public int updateNonCheckService(HttpServletRequest request)throws Exception;
	public int deleteTodoService(HttpServletRequest request)throws Exception;
	public String findC_numDo(HttpServletRequest request)throws Exception;
}
