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
import org.sst.action.PersonalStudy_insertStudyTimeFormAction;
import org.sst.action.PersonalStudy_insertTimeAction;

/**
 * Servlet implementation class PersonalStudyController
 */
@WebServlet("/personalstudy/*")
public class PersonalStudyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PersonalStudyController() {
        super();
    }
    public void doProcess(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	System.out.println(requestURI);
    	System.out.println(contextPath);
    	String command = requestURI.substring(contextPath.length()+15);
    	System.out.println(command);
        
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(command.equals("insertStudyTimeForm.do")) {
    		action = new PersonalStudy_insertStudyTimeFormAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("insertTimeAction.do")) {
    		action = new PersonalStudy_insertTimeAction();
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
