package org.sst.service;

import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD
=======
import org.sst.domain.Criteria;
import org.sst.domain.StudyGroupVO;
>>>>>>> refs/remotes/origin/main
import org.sst.domain.WANoteVO;

public interface WANoteService {
	List<WANoteVO> listWANote(String m_id);
	boolean createWANote(WANoteVO vo);
	boolean  updateWANote(WANoteVO vo);
	boolean  deleteWANote(String w_num);
	WANoteVO readWANote(String w_num);
	
<<<<<<< HEAD
	List<WANoteVO> listWithPagingWANote(HashMap map);
	int getTotalCount(HashMap map);
=======
	List<WANoteVO> listWithPagingWANote(Criteria cri);
	int getTotalCount(Criteria cri);
	List<WAtagVO> listAllTag(String keyword);
	
	String createTag(WAtagVO vo);
	WAtagVO readTag(WAtagVO vo);
	
	int createWATag(HashMap map);
	List<WAtagVO> listWATag(String w_num);
	int deleteAllWaTag(String w_num);
	
	List<HashMap> countTagChart(String m_id);
	List<HashMap> countReasonChart(String m_id);
	
	List<WANoteVO> listWithTagWANote(Criteria cri);
	int getTotalCountTag(Criteria cri);
	
	List<StudyGroupVO> recommendStudyGrop(String tg_name);
>>>>>>> refs/remotes/origin/main
}
