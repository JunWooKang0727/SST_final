package org.sst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sst.domain.Criteria;
import org.sst.domain.QuestionReplyPageVO;
import org.sst.domain.QuestionReplyVO;


public interface QuestionReplyService {
	
	public int register(QuestionReplyVO vo);
	
	public QuestionReplyVO get(String rno);
	
	public int modify(QuestionReplyVO vo);
	
	public int remove(String rno);
	
	public List<QuestionReplyVO> getList(Criteria cri, String q_num);
	
	public QuestionReplyPageVO getListPage(Criteria cri, String q_num);
	
	//댓글추천
	public int updateLike(String rno) throws Exception;
	 
	public int updateHate(String rno) throws Exception;
}
