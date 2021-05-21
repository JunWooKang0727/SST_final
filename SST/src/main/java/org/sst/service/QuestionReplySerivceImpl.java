package org.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sst.domain.Criteria;
import org.sst.domain.QuestionReplyPageVO;
import org.sst.domain.QuestionReplyVO;
import org.sst.mapper.QuestionMapper;
import org.sst.mapper.QuestionReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
@AllArgsConstructor
public class QuestionReplySerivceImpl implements QuestionReplyService {

	@Setter(onMethod_= @Autowired)
	private QuestionReplyMapper mapper;
	
	@Setter(onMethod_= @Autowired)
	private QuestionMapper questionMapper;
	
	@Transactional
	@Override
	public int register(QuestionReplyVO vo) {
		log.info("register....."+vo);
		questionMapper.updateReplyCnt(vo.getQ_num(), 1);
		return mapper.insert(vo);
	}

	@Override
	public QuestionReplyVO get(String rno) {
		log.info("get......"+rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(QuestionReplyVO vo) {
		log.info("modify......."+vo);
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(String rno) {
		log.info("remove....."+rno);
		
		QuestionReplyVO vo = mapper.read(rno);
		
		questionMapper.updateReplyCnt(vo.getQ_num(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<QuestionReplyVO> getList(Criteria cri, String q_num) {
		log.info("get Reply List of a Board"+q_num);
		return mapper.getListWithPaging(cri, q_num);
	}

	@Override
	public QuestionReplyPageVO getListPage(Criteria cri, String q_num) {
		// TODO Auto-generated method stub
		return new QuestionReplyPageVO(mapper.getCountByBno(q_num), mapper.getListWithPaging(cri, q_num));
	}
	
	

}
