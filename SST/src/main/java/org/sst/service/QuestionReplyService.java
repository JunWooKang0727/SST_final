package org.sst.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sst.domain.Criteria;
import org.sst.domain.QuestionReplyPageVO;
import org.sst.domain.QuestionReplyVO;


public interface QuestionReplyService {
	
	public int register(QuestionReplyVO vo);
	
	public QuestionReplyVO get(Long rno);
	
	public int modify(QuestionReplyVO vo);
	
	public int remove(Long rno);
	
	public List<QuestionReplyVO> getList(Criteria cri, Long bno);
	
	public QuestionReplyPageVO getListPage(Criteria cri, Long bno);
	
	
}
