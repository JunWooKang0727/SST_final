package org.sst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.GroupMemberVO;
import org.sst.mapper.GroupMemberMapper;
import org.sst.mapper.StudyNoteMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

	@Setter(onMethod_=@Autowired)
	private GroupMemberMapper mapper;
	
	@Override
	public GroupMemberVO gmRead(GroupMemberVO vo) {
		
		return mapper.getGm_numById(vo);
	}

}
