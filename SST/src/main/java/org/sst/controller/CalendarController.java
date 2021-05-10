package org.sst.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sst.action.Action;
import org.sst.action.ActionForward;
import org.sst.action.Calendar_CheckTodoAction;
import org.sst.action.Calendar_MainAction;
import org.sst.action.Calendar_NonCheckTodoAction;
import org.sst.action.Calendar_deleteTodoAction;
import org.sst.action.Calendar_insertTodoAction;
import org.sst.action.Calendar_insertTodoFormAction;

@WebServlet("/fullcalendar-5.6.0/Calendar2/*")
public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CalendarController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doProcess(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	System.out.println(requestURI);
    	System.out.println(contextPath);
    	String command = requestURI.substring(contextPath.length()+29);
    	System.out.println(command);
        
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(command.equals("/insertTodoForm.do")) {
    		action = new Calendar_insertTodoFormAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}else if(command.equals("/insertTodo.do")) {
    		action = new Calendar_insertTodoAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("/CalendarForm.do")) {
    		action = new Calendar_MainAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}else if(command.equals("/CheckTodo.do")) {
			action = new Calendar_CheckTodoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/NonCheckTodo.do")) {
			action = new Calendar_NonCheckTodoAction();
			try {
				System.out.println("\n\n==1==\n\n\n");
				forward = action.execute(request, response);
				System.out.println("\n\n==2==\n\n\n");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\n\n==3==\n\n\n");
			}
			System.out.println("\n\n==4==\n\n\n");
		}else if(command.equals("/CheckTodo.do")) {
			action = new Calendar_CheckTodoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/DeleteTodo.do")) {
			action = new Calendar_deleteTodoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
    	
    	
    	if(forward!=null) {
    		if(forward.isRedirect()) {
    			System.out.println("\n안됨?");
    			response.sendRedirect(forward.getPath());
    			System.out.println("\n안됨?4");
    			
    		}else {
    			RequestDispatcher dispatcher = 
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
