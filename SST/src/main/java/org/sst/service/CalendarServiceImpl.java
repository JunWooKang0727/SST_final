package org.sst.service;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.sst.domain.CalendarTodoVO;


public class CalendarServiceImpl implements CalendarSevice{
	
	@Override
	public int insertTodoService(HttpServletRequest request)throws Exception{

		
		request.setCharacterEncoding("utf-8");
		CalendarTodoVO todo = new CalendarTodoVO();
		
		todo.setT_category(request.getParameter("t_category"));
		todo.setT_title(request.getParameter("t_title"));
		todo.setT_contents(request.getParameter("t_contents"));
		String stdate = request.getParameter("t_startdate");
		
		SimpleDateFormat st_transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = st_transFormat.parse(stdate);
		todo.setT_startdate(startdate);
		//오류가 나버리누.. ;;
		if(request.getParameter("t_enddate")!=null) {
			String endate = request.getParameter("t_enddate");
			SimpleDateFormat en_transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date enddate = en_transFormat.parse(endate);
			todo.setT_enddate(enddate);
		}
		System.out.println(todo + "인서트서비스 todo입니다.");
		return dao.insertTodo(todo);
	}
	@Override
	public List<CalendarTodoVO> listTodo()throws Exception{
		List<CalendarTodoVO> list = dao.listTodo();
		System.out.println(list + "서비스 list입니다.");
		return list;
	}
	@Override
	public int updateCheckService(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("utf-8");
		CalendarTodoVO todo = new CalendarTodoVO();
		
		todo.setT_num(request.getParameter("t_num"));
		System.out.println(todo.getT_num() + "업데이트 쳌서비스 todo입니다.");
		return dao.updateCheckTodo(todo);
	}
	@Override
	public int updateNonCheckService(HttpServletRequest request)throws Exception{
		System.out.println("==1-3-1==");
		request.setCharacterEncoding("utf-8");
		System.out.println("==1-3-2==");
		CalendarTodoVO todo = new CalendarTodoVO();
		System.out.println("==1-3-3==");
		todo.setT_num(request.getParameter("t_num"));
		System.out.println("==1-3-4==");
		System.out.println(todo.getT_num() + "업데이트 넌쳌서비스 todo입니다.");
		System.out.println("==1-3-5==");
		return dao.updateNonCheckTodo(todo);
	}
	@Override
	public int deleteTodoService(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("utf-8");
		CalendarTodoVO todo = new CalendarTodoVO();
		
		todo.setT_num(request.getParameter("t_num"));
		System.out.println(todo.getT_num() + "딜리트 쳌서비스 todo입니다.");
		return dao.deleteTodo(todo);
	}
	@Override
	public String findC_numDo(HttpServletRequest request)throws Exception {
		HttpSession session = request.getSession();
		MemberSessionVO temp = (MemberSessionVO) session.getAttribute("m_id");
		return null;

	}
	
}
