package org.sst.service;

import javax.servlet.http.HttpServletRequest;

import org.sst.domain.PersonalStudyVO;
import org.sst.repository.PersonalStudyDAO;

public class PersonalStudyServiceImpl implements PersonalStudyService {
	private static PersonalStudyServiceImpl service = new PersonalStudyServiceImpl();
	private static PersonalStudyDAO dao;
	
	public static PersonalStudyServiceImpl getInstance() {
		dao = PersonalStudyDAO.getInstance();
		return service;
	}
	@Override
	public int insertStudyTimeService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		PersonalStudyVO studytime = new PersonalStudyVO();
		studytime.setPs_place(request.getParameter("ps_place"));
		studytime.setPs_category(request.getParameter("ps_category"));
		studytime.setPs_time(request.getParameter("ps_time"));
		
		
		
		
		return dao.insertStudyTime(studytime);
	}

}
