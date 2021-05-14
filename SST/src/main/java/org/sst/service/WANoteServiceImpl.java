package org.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.WANoteVO;
import org.sst.mapper.ReportCardMapper;
import org.sst.mapper.WANoteMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class WANoteServiceImpl implements WANoteService{
	@Setter(onMethod_ = @Autowired)
	private WANoteMapper mapper;
	
	@Override
	public List<WANoteVO> listWANote(String m_id) {
		return mapper.listWANote(m_id);
	}

	@Override
	public boolean  createWANote(WANoteVO vo) { 
		return mapper.createWANote(vo)==1;
	}

	@Override
	public boolean  updateWANote(WANoteVO vo) {
		return mapper.updateWANote(vo)==1;
	}

	@Override
	public boolean  deleteWANote(String w_num) {
		return mapper.deleteWANote(w_num)==1;
	}

	@Override
	public WANoteVO readWANote(String w_num) {
		return mapper.readWANote(w_num);
	}

}
