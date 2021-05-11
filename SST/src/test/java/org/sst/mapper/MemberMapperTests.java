package org.sst.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	// 회원가입
	@Test
	public void testMemberInsert(){
		MemberVO mem = new MemberVO("test0620", "test0510", "test0510", "0101111", "999999", "test0620");
		mapper.memberInsert(mem);
		log.info(mem);
	}
	
	// 회원정보 조회
	@Test
	public void testMemberRead(){
		MemberVO mem = mapper.memberRead("test0620");
		log.info(mem);
	}
	
	// 회원정보 수정
	@Test
	public void testMemberModify(){
		MemberVO mem = mapper.memberRead("test0620");
		mem.setM_phone("11111");
		mapper.memberUpdate(mem);
	}

	// 회원탈퇴
	@Test
	public void testMemberDelete(){
		MemberVO mem = new MemberVO();
		mem.setM_id("test0620");
		mem.setM_pw("test0510");
		mapper.memberDelete(mem);
	}
	
	// 아이디 중복 확인
	
	@Test
	public void testMemberIdCheck(){
		int count = mapper.memberIdCheck("member01");
		log.info(count);
	}
	
}
