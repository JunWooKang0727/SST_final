package org.sst.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sst.domain.StudyGroupVO;
import org.sst.service.StudyGroupService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/group/*")
@AllArgsConstructor
public class StudyGroupController {
	
	private StudyGroupService service;
	
	// 메인 페이지에서 회원이 생성한 그룹, 참여중인 그룹, 
	@GetMapping("/main")
	public void groupMainPage(Principal principal, Model model){
		log.info("[Group Home Page]");
		log.info("[get my group list]");
		model.addAttribute("mygrouplist", service.myGroupGet(principal.getName()));
	}
	
	@GetMapping("/create")
	public void groupCreate(){
		log.info("[Group Create Page GET]");
	}
	
	@PostMapping("/create")
	public void groupCreate(StudyGroupVO group, Principal principal){
		log.info("[Group Create Page POST]" + group);
		service.groupCreate(group, principal.getName());
	}
	
	@GetMapping({"/read", "/update"})
	public void groupDetailRead(@RequestParam("g_num") String g_num, Model model){
		log.info("[Group Read / Update Page GET]");
		model.addAttribute("group", service.groupDetailGet(g_num));
		log.info(service.groupDetailGet(g_num));
	}
	
	@PostMapping("/update")
	public String groupUpdate(StudyGroupVO group){
		log.info("[Group Update Page POST]");
		log.info(group.getG_num());
		log.info(service.groupModify(group));
		return "redirect:/group/read?g_num=" + group.getG_num();
	}
	
	@PostMapping("/remove")
	public String groupDetail(StudyGroupVO group){
		log.info("[Group Detail]");
		log.info(group.getG_num());
		service.groupRemove(group.getG_num());
		return "redirect:/group/main";
	}
	
}
