package org.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.sst.domain.Criteria;
import org.sst.domain.QuestionSearchVO;
import org.sst.domain.QuestionVO;




public interface QuestionMapper {
	public int insertQuestion(QuestionVO qNote);

	//
	public List<QuestionVO> listQuestion(QuestionSearchVO search, RowBounds row);
	
	public QuestionVO detailQuestion(String q_num);

	public int updateQuestion(QuestionVO question);

	public int countQuestion(QuestionSearchVO search);
	public int deleteQuestion(String q_num);
	
	public List<QuestionVO> getQuestionList(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount")int amount);
}
