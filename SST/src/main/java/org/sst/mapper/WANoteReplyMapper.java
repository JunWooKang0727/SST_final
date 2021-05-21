package org.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sst.domain.Criteria;
import org.sst.domain.WANoteReplyVO;


public interface WANoteReplyMapper {
	int createWANoteReply(WANoteReplyVO vo);
	int updateWANoteReply(WANoteReplyVO vo);
	int deleteWANoteReply(String wr_num);
	
	WANoteReplyVO readWANoteReply(String wr_num);
	List<WANoteReplyVO> listWANoteReply(@Param("cri") Criteria cri,@Param("w_num") String w_num);
}
