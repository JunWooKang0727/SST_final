package org.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sst.domain.Criteria;
import org.sst.domain.QuestionReplyVO;


public interface QuestionReplyMapper {

	public int insert(QuestionReplyVO vo);

	public QuestionReplyVO read(String q_num);

	public int delete(String targetRno);

	public int update(QuestionReplyVO reply);

	public List<QuestionReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("q_num") String q_num);
	
	public int getCountByBno(String q_num);
	
	//
	
	public int updateLike(String rno);
	public int updateHate(String rno);
}
