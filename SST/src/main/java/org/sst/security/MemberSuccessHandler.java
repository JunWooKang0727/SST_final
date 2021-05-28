package org.sst.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class MemberSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		//1
		log.warn("Login success......................");
		
		List<String> roleNames = new ArrayList<>();
		
		log.warn("Login success222222......................");
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
			log.warn("Login success33......................");
		});
		
		for(int i=0; i<roleNames.size(); i++){
			log.info(roleNames.get(i));
		}
		
		roleNames.forEach(s -> log.info(s));
		if(roleNames.contains("ROLE_MEMBER")){
			log.info("hello");
			response.sendRedirect("/calendar/list");
			return;
		}
		// 권한 없으면 /member/main으로 이동
		log.info("hello2");
		response.sendRedirect("/");
	}

}
