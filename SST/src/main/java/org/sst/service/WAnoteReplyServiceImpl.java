package org.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.Criteria;
import org.sst.domain.WANoteReplyVO;
import org.sst.mapper.WANoteReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class WAnoteReplyServiceImpl implements WAnoteReplyService{
	@Setter(onMethod_ = @Autowired)
	private WANoteReplyMapper mapper;
	
	
	@Override
	public boolean createWANoteReply(WANoteReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.createWANoteReply(vo)==1;
	}

	@Override
	public boolean updateWANoteReply(WANoteReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.updateWANoteReply(vo)==1;
	}

	@Override
	public boolean deleteWANoteReply(String wr_num) {
		// TODO Auto-generated method stub
		return mapper.deleteWANoteReply(wr_num)==1;
	}

	@Override
	public WANoteReplyVO readWANoteReply(String wr_num) {
		// TODO Auto-generated method stub
		return mapper.readWANoteReply(wr_num);
	}

	@Override
	public List<WANoteReplyVO> listWANoteReply(Criteria cri, String w_num) {
		// TODO Auto-generated method stub
		return mapper.listWANoteReply(cri, w_num);
	}

}
