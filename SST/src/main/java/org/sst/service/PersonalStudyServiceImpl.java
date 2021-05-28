package org.sst.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.PersonalStudyVO;
import org.sst.mapper.PersonalStudyMapper;

import lombok.Setter;

@Service
public class PersonalStudyServiceImpl implements PersonalStudyService {
	@Setter(onMethod_=@Autowired)
	private PersonalStudyMapper mapper;
	
	
	
	@Override
	public int register(PersonalStudyVO vo,String id){
		vo.setM_id(id);
		
		return mapper.insertStudyTime(vo);
	}

}
