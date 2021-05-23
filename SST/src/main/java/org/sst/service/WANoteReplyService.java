package org.sst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sst.domain.Criteria;
import org.sst.domain.WANoteReplyVO;

public interface WANoteReplyService {
	boolean createWANoteReply(WANoteReplyVO vo);

	boolean updateWANoteReply(WANoteReplyVO vo);

	boolean deleteWANoteReply(String wr_num);

	WANoteReplyVO readWANoteReply(String wr_num);

	List<WANoteReplyVO> listWANoteReply(Criteria cri, String w_num);

	int countWANoteReply(String w_num);

	String sb();
}
