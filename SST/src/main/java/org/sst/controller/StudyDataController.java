package org.sst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sst.service.StudyNoteService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/studydata/*")
@AllArgsConstructor
public class StudyDataController {

	
	@GetMapping("/list2")
	public void goList(){
		
	}
}
