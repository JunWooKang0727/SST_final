package org.sst.service;

import org.springframework.stereotype.Service;
import org.sst.domain.MemberVO;
import org.sst.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	private MemberMapper mapper;
	
	@Override
	public void memberSignup(MemberVO member) {
		log.info("[member signUp]" + member);
		mapper.memberInsert(member);
		mapper.memberAuth(member.getM_id()); // 가입과 동시에 권한 추가
	}

	@Override
	public MemberVO memberGet(String id) {
		log.info("[member Get]" + id);
		return mapper.memberRead(id);
	}

	@Override
	public boolean memberModify(MemberVO member) {
		log.info("[member Modify]" + member);
		return mapper.memberUpdate(member) == 1;
	}

	@Override
	public boolean memberRemove(String id) {
		log.info("[member Remove]" + id);
		return mapper.memberDelete(id) == 1;
	}

	@Override
	public int memberIdCount(String id) {
		log.info("[ " + id + "member Id Count]");
		return mapper.memberIdCheck(id);
	}

}
