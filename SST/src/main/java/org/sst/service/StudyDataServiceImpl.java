package org.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.StudyDataListVO;
import org.sst.domain.StudyDataVO;
import org.sst.mapper.StudyDataMapper;

import lombok.Setter;

@Service
public class StudyDataServiceImpl implements StudyDataService {

	@Setter(onMethod_=@Autowired)
	private StudyDataMapper mapper;
	
	@Override
	public List<StudyDataVO> getList(StudyDataListVO vo) {
		// TODO Auto-generated method stub
		return mapper.findByG_num(vo);
	}

	@Override
	public int upload(StudyDataVO vo) {
		
		return mapper.insert(vo);
	}

	@Override
	public int delete(String uuid) {
		// TODO Auto-generated method stub
		return mapper.delete(uuid);
	}

}
