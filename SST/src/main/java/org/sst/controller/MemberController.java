package org.sst.controller;

import org.springframework.stereotype.Controller;
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
	
	// 회원가입 페이지 이동
	@GetMapping("/create")
	public void signUp(){
		log.info("[Member SignUp GET]");
	}
	
	// 회원 가입 요청
	@PostMapping("/create")
	public String signUp(MemberVO member, RedirectAttributes rttr){
		log.info("[Member SignUp POST]" + member);
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
	@PostMapping("/checkId")
	public String CheckId(@RequestParam String id){
		log.info("[Member Id Check Post]" + " : " + id);
		int result = service.memberIdCount(id);
		if(result == 0){
			return "canuse";
		} else {
			return "cantuse";
		}
	}
}
