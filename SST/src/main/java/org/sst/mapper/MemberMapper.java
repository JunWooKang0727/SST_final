package org.sst.mapper;

import org.sst.domain.MemberVO;

public interface MemberMapper {
		// 회원가입
		public void memberInsert(MemberVO member);
		// 회원정보 조회
		public MemberVO memberRead(String id);
		// 회원정보 수정
		public int memberUpdate(MemberVO member);
		// 회원정보 삭제
		public int memberDelete(MemberVO member);
		// 아이디 중복 체크
		public int memberIdCheck(String id);
}
