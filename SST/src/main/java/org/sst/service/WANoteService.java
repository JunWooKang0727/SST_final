package org.sst.service;

import java.util.HashMap;
import java.util.List;

import org.sst.domain.WANoteVO;
import org.sst.domain.WAtagVO;

public interface WANoteService {
	List<WANoteVO> listWANote(String m_id);
	boolean createWANote(WANoteVO vo);
	boolean  updateWANote(WANoteVO vo);
	boolean  deleteWANote(String w_num);
	WANoteVO readWANote(String w_num);
	
	List<WANoteVO> listWithPagingWANote(HashMap map);
	int getTotalCount(HashMap map);
	List<WAtagVO> listAllTag(String keyword);
}
