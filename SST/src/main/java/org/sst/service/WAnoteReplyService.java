package org.sst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sst.domain.Criteria;
import org.sst.domain.WANoteReplyVO;

public interface WAnoteReplyService {
	public boolean createWANoteReply(WANoteReplyVO vo);
	public boolean updateWANoteReply(WANoteReplyVO vo);
	public boolean deleteWANoteReply(String wr_num);
	
	public WANoteReplyVO readWANoteReply(String wr_num);
	public List<WANoteReplyVO> listWANoteReply(Criteria cri,String w_num);
}
