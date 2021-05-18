package org.sst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sst.service.StudyGroupService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/group/*")
@AllArgsConstructor
public class StudyGroupController {
	
	private StudyGroupService service;
	
	@GetMapping("/main")
	public void groupMainPage(){
		log.info("[Group Home Page]");
	}
	
}
