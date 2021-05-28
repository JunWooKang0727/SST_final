package org.sst.controller;



import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sst.domain.PersonalStudyVO;
import org.sst.service.PersonalStudyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/personalstudy/*")
@AllArgsConstructor
public class PersonalStudyController{
	
	private PersonalStudyService service;
	
	@GetMapping("/create")
	public void create(){
		
	}
	@PostMapping("/create")
	public String create(PersonalStudyVO vo,Principal principal){
		log.info("study======================="+vo);
		String id = principal.getName();
		service.register(vo,id);
		
		return "redirect:/personalstudy/create";
	}

}
