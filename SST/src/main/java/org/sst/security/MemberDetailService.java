package org.sst.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.sst.domain.MemberVO;
import org.sst.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/*
 * MemberDetailService의 UserDetails 인터페이스  
 * DB에서 Member의 정보를 가져온다. UserDetails 인터페이스 메서드에서 Member 정보를 
 * 가져와서 AuthenticationProvider(security-context.xml) 인터페이스로 Member의 정보를 리턴하면
 * 
 * 사용자가 입력한 정보와 DB에 있는 Memb
 */
@Log4j
public class MemberDetailService implements UserDetailsService {

	@Setter(onMethod_ = {@Autowired})
	private MemberMapper mapper;
	
	// Member 정보를 가져오기 위해 구현해야하는 인터페이스 
	// 리턴타입은 UserDetail이다. 
	// MemberVO의 인스턴스를 UserDetail 타입으로 변환해야함.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("Get Member.............." + username);
		MemberVO member = mapper.MemberloginRead(username);
		log.warn("Queried by member mapper................" + member);
		return member == null ? null : new CustomMemberVO(member);
	}

}
