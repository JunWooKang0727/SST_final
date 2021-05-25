package org.sst.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sst.domain.PersonalCrawlerVO;
import org.sst.domain.PersonalMakeVO;
import org.sst.service.CalendarSevice;
import org.sst.service.PersonalCrawlMakeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/personalcrawlmake/*")
@AllArgsConstructor
public class PersonalCrawlMakeController {
	
	private PersonalCrawlMakeService service;
	
	@PostMapping("/create")
	public String create(PersonalCrawlerVO pcvo,PersonalMakeVO pmvo) throws IOException{
		service.makePdf(pcvo, pmvo);
		System.out.println(pcvo.toString()+"받아온 크롤값입니다.");
		System.out.println(pmvo.toString()+"받아온 문제값입니다.");
		return "redirect:/personalstudy/create";
	}
}
