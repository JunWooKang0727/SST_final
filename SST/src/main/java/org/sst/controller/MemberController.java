package org.sst.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sst.domain.MemberVO;
import org.sst.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/*")
@AllArgsConstructor
public class MemberController {
	
	private MemberService service;
	
	private BCryptPasswordEncoder pwEncoder;

	// 메인 페이지 : 모두 접근 가능
	@GetMapping({"/main", "/logout"})
	public void mainPage(){
		log.info("[Main Page || Logout Page]");
	}
	
	// 로그인 후 메인 페이지 : 로그인 한 사용자만 접근 가능
	@GetMapping("/home")
	public void afterLoginPage(){
		log.info("[After Login Page]");
	}
	
	// 회원가입 페이지 이동
	@GetMapping("/create")
	public void signUp(){
		log.info("[Member SignUp GET]");
	}
	
	// 회원 가입 요청
	@PostMapping("/create")
	public String signUp(MemberVO member){
		log.info("[Member SignUp POST]" + member);
		member.setM_pw(pwEncoder.encode(member.getM_pw()));
		service.memberSignup(member);
		return "redirect:/member/joinFin";
	}
	
	// 회원 가입 완료
	@GetMapping("/joinFin")
	public void SignUpFin(){
		log.info("[Member SignUpFin GET]");
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@GetMapping("/checkId")
	public String CheckId(@RequestParam(value="id") String id){
		log.info(id);
		log.info("[Member Id Check Get]" + " : " + id);
		//int id_result = service.memberIdCount(id.replace("=", ""));
		int id_result = service.memberIdCount(id);
		if(id_result == 0){
			return "success";
		} else {
			return "fail";
		}
	}
	
	@GetMapping("/read")
	public void memberRead(Principal principal, Model model){
		String id = principal.getName();
		log.info("member get.......");
		model.addAttribute("member", service.memberGet(id));
	}
	
	@PostMapping("/update")
	public String memberUpdate(MemberVO member){
		service.memberModify(member);
		log.info("member update.......");
		return "redirect:/member/read";
	}
	
	@GetMapping("/delete")
	public void memberDelete(){
		log.info("member delete page............");
	}
	
	@PostMapping("/delete")
	public String memberDelete(Principal principal, @RequestParam("password") String password){
		MemberVO member = service.memberGet(principal.getName());
		boolean result = pwEncoder.matches(password, member.getM_pw());
		
		if(result == true){
			service.memberRemove(principal.getName());
			SecurityContextHolder.clearContext();
			log.info("회원탈퇴 성공");
			return "redirect:/member/main";
		}
		return "redirect:/member/delete";
	}
	
	// 로그인 페이지
	@GetMapping("/login")
	public void MemberLogin(){
		log.info("[Member login Get]");
	}
	
}
