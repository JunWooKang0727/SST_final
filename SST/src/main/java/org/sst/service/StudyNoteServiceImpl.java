package org.sst.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.Criteria;
import org.sst.domain.StudyNoteListVO;
import org.sst.domain.StudyNoteSearchVO;
import org.sst.domain.StudyNoteVO;
import org.sst.mapper.StudyNoteMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class StudyNoteServiceImpl implements StudyNoteService {

	@Setter(onMethod_=@Autowired)
	private StudyNoteMapper mapper;
	

	@Override
	public int register(StudyNoteVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertStudyNote(vo);
	}


	@Override
	public List<StudyNoteVO> getList(Criteria cri) {
		
		return mapper.getStudyNoteList(cri);
	}


	@Override
	public StudyNoteVO read(String sn_num) {
		
		return mapper.detailStudyNote(sn_num);
	}


	@Override
	public int update(StudyNoteVO vo) {
		
		return mapper.updateStudyNote(vo);	
	}


	@Override
	public int delete(String sn_num) {
		
		return mapper.deleteStudyNote(sn_num);
	}


	@Override
	public int getTotal(Criteria cri) {
	
		return mapper.getTotalCount(cri);
	}


}
