package org.sst.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.sst.domain.MemberVO;

import lombok.Getter;

@Getter
public class CustomMemberVO extends User {
	//1
	private static final long serialVerisonUID = 1L;
	
	private MemberVO member;
	
	public CustomMemberVO(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomMemberVO(MemberVO member) {
		super(member.getM_id(), member.getM_pw(), member.getAuthList().stream().
				map(auth->new SimpleGrantedAuthority(auth.getAuth())).
				collect(Collectors.toList()));
		
	}
}
