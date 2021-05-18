package org.sst.service;

import java.util.List;

import org.sst.domain.StudyDataVO;

public interface StudyDataService {
	
	public List<StudyDataVO> getList(String g_num,String curFolder);
	
	public int upload(StudyDataVO vo);
}
