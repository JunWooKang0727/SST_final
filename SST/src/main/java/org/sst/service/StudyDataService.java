package org.sst.service;

import java.util.List;

import org.sst.domain.StudyDataListVO;
import org.sst.domain.StudyDataVO;

public interface StudyDataService {
	
	public List<StudyDataVO> getList(StudyDataListVO vo);
	
	public int upload(StudyDataVO vo);
	
	public int delete(String uuid);
}
