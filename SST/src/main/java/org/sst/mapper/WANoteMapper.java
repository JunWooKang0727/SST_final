package org.sst.mapper;

import java.util.HashMap;
import java.util.List;

import org.sst.domain.Criteria;
import org.sst.domain.WANoteVO;

public interface WANoteMapper {
	List<WANoteVO> listWANote(String m_id);
	
	List<WANoteVO> listWithPagingWANote(HashMap map);
	int createWANote(WANoteVO vo);
	int updateWANote(WANoteVO vo);
	int deleteWANote(String w_num);
	WANoteVO readWANote(String w_num);
	
}
