package org.sst.service;

import java.util.List;

import org.sst.domain.Criteria;
import org.sst.domain.QuestionVO;


public interface QuestionService {
	
	public int register(QuestionVO vo);
	
	public List<QuestionVO> getList(Criteria cri);
	
	public QuestionVO read(String q_num);
	
	public int update(QuestionVO vo);
	
	public int delete(String q_num);
	
	public int getTotal(Criteria cri);
	
}
