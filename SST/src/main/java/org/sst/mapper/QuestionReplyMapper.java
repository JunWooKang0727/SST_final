package org.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sst.domain.Criteria;
import org.sst.domain.QuestionReplyVO;


public interface QuestionReplyMapper {

	public int insert(QuestionReplyVO vo);

	public QuestionReplyVO read(Long bno);

	public int delete(Long targetRno);

	public int update(QuestionReplyVO reply);

	public List<QuestionReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	public int getCountByBno(Long bno);
}
