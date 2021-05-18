package org.sst.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private MemberService service;
	
	@Setter(onMethod_ = {@Autowired})
	private BCryptPasswordEncoder encoder;
	
	// MemberService 확인
	/*@Test
	public void testExist(){
		log.info("[service inject]" + service);
		assertNotNull(service);
	}*/
	
	/*@Test
	public void testMemberSignup(){
		log.info("[SignUp Service]");
		MemberVO mem = new MemberVO();
		mem.setM_id("test06201");
		mem.setM_pw("test06201");
		mem.setM_phone("test06201");
		mem.setM_email("test06201");
		mem.setM_birth("test06201");
		mem.setM_name("test06201");
		service.memberSignup(mem);
	}*/
	
	/*@Test
	public void testMemberGet(){
		log.info("[MemberGet Service]");
		service.memberGet("mem1042");
	}*/
	
	/*@Test
	public void testMemberModify(){
		log.info("[MemberModify Service]");
		MemberVO mem = service.memberGet("mem1042");
		mem.setM_phone("1048");
		service.memberModify(mem);
	}*/
	
	/*@Test
	public void testMemberIdCheck(){
		log.info("[Member Id Check Service]");
		service.memberIdCount("member01");
	}*/
}
