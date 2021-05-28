package org.sst.service;
import java.util.List;

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
	public int register(CalendarTodoVO vo, String id){
		vo.setM_id(id);
		return mapper.insertTodo(vo);
	}

	@Override
	public List<CalendarTodoVO> list(String id){
		
		return mapper.listTodo(id);
	}

	@Override
	public int updateCheck(CalendarTodoVO vo, String id){
		vo.setM_id(id);
		return mapper.updateCheckTodo(vo);
	}

	@Override
	public int updateNonCheck(CalendarTodoVO vo, String id){
		vo.setM_id(id);
		return mapper.updateNonCheckTodo(vo);
	}

	@Override
	public int delete(CalendarTodoVO vo, String id){
		vo.setM_id(id);
		return mapper.deleteTodo(vo);
	}
	
}
