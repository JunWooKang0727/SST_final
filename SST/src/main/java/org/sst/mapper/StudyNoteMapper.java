package org.sst.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.sst.domain.Criteria;
import org.sst.domain.StudyNoteSearchVO;
import org.sst.domain.StudyNoteVO;



public interface StudyNoteMapper {
	public int insertStudyNote(StudyNoteVO stdNote);

	//
	public List<StudyNoteVO> listStudyNote(StudyNoteSearchVO search, RowBounds row);
	
	public StudyNoteVO detailStudyNote(String sn_num);

	public int updateStudyNote(StudyNoteVO studynote);

	public int countStudyNote(StudyNoteSearchVO search);
	public int deleteStudyNote(String sn_num);
	
	public List<StudyNoteVO> getStudyNoteList(Criteria cri);
	
	public int getTotalCount(Criteria cri);

}
