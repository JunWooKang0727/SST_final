package org.sst.service;

import java.util.List;

import org.sst.domain.Criteria;
import org.sst.domain.StudyNoteVO;


public interface StudyNoteService {
	
	public int register(StudyNoteVO vo);
	
	public List<StudyNoteVO> getList(Criteria cri);
	
	public StudyNoteVO read(String sn_num);
	
	public int update(StudyNoteVO vo);
	
	public int delete(String sn_num);
	
	public int getTotal(Criteria cri);
	
}
