package org.sst.mapper;

import java.util.List;

import org.sst.domain.StudyDataListVO;
import org.sst.domain.StudyDataVO;

public interface StudyDataMapper {
	
	public int insert(StudyDataVO vo);
	
	public int delete(String uuid);
	
	public List<StudyDataVO> findByG_num(StudyDataListVO vo);
}
