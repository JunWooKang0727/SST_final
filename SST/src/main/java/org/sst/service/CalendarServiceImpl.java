package org.sst.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.CalendarTodoVO;
import org.sst.mapper.CalendarMapper;

import lombok.Setter;

@Service
public class CalendarServiceImpl implements CalendarSevice{
	@Setter(onMethod_=@Autowired)
	private CalendarMapper mapper;

	@Override
	public int register(CalendarTodoVO vo){
		
		return mapper.insertTodo(vo);
	}

	@Override
	public List<CalendarTodoVO> list(){
		
		return mapper.listTodo();
	}

	@Override
	public int updateCheck(CalendarTodoVO vo){
		// TODO Auto-generated method stub
		return mapper.updateCheckTodo(vo);
	}

	@Override
	public int updateNonCheck(CalendarTodoVO vo){
		// TODO Auto-generated method stub
		return mapper.updateNonCheckTodo(vo);
	}

	@Override
	public int delete(CalendarTodoVO vo){
		// TODO Auto-generated method stub
		return mapper.deleteTodo(vo);
	}
	
}
