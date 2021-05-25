package org.sst.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.Criteria;
import org.sst.domain.WANoteVO;
import org.sst.domain.WAtagVO;
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
	public String createWANote(WANoteVO vo) { 
		mapper.createWANote(vo);
		return vo.getW_num();
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

	@Override
	public List<WANoteVO> listWithPagingWANote(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.listWithPagingWANote(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<WAtagVO> listAllTag(String keyword) {
		// TODO Auto-generated method stub
		return mapper.listAllTag(keyword);
	}

	@Override
	public String createTag(WAtagVO vo) {
		// TODO Auto-generated method stub
		mapper.createTag(vo);
		return vo.getTg_num();
	}

	@Override
	public WAtagVO readTag(WAtagVO vo) {
		// TODO Auto-generated method stub
		return mapper.readTag(vo);
	}

	@Override
	public int createWATag(HashMap map) {
		// TODO Auto-generated method stub
		return mapper.createWATag(map);
	}

	@Override
	public List<WAtagVO> listWATag(String w_num) {
		// TODO Auto-generated method stub
		return mapper.listWATag(w_num);
	}
	
	

	@Override
	public List<HashMap> countTagChart(String m_id) {
		// TODO Auto-generated method stub
		return mapper.countTagChart(m_id);
	}

	@Override
	public List<HashMap> countReasonChart(String m_id) {
		// TODO Auto-generated method stub
		return mapper.countReasonChart(m_id);
	}

	@Override
	public List<WANoteVO> listWithTagWANote(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.listWithTagWANote(cri);
	}

	@Override
	public int getTotalCountTag(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getTotalCountTag(cri);
	}

	@Override
	public int deleteAllWaTag(String w_num) {
		// TODO Auto-generated method stub
		return mapper.deleteAllWaTag(w_num);
	}

}
