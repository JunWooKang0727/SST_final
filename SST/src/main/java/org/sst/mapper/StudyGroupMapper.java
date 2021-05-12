package org.sst.mapper;

import org.sst.domain.StudyGroupVO;

public interface StudyGroupMapper {
	// 그룹생성
	public void groupInsert(StudyGroupVO group);
	
	public StudyGroupVO groupDetailRead(String gnum);
	
	public int groupUpdate(StudyGroupVO group);

	public int groupDelete(String gnum);
}
