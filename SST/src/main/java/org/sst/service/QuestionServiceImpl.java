package org.sst.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.Criteria;
import org.sst.domain.QuestionVO;
import org.sst.mapper.QuestionMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class QuestionServiceImpl implements QuestionService {

	@Setter(onMethod_=@Autowired)
	private QuestionMapper mapper;
	

	@Override
	public int register(QuestionVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertQuestion(vo);
	}


	@Override
	public List<QuestionVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getQuestionList(cri);
	}


	@Override
	public QuestionVO read(String q_num) {
		
		return mapper.detailQuestion(q_num);
	}


	@Override
	public int update(QuestionVO vo) {
		
		return mapper.updateQuestion(vo);	
	}


	@Override
	public int delete(String q_num) {
		
		return mapper.deleteQuestion(q_num);
	}


	@Override
	public int getTotal(Criteria cri) {
	
		return mapper.getTotalCount(cri);
	}


}
