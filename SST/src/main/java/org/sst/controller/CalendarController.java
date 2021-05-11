package org.sst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sst.domain.CalendarTodoVO;
import org.sst.service.CalendarSevice;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/fullcalendar-5.6.0/Calendar/*")
@AllArgsConstructor
public class CalendarController {
	
	private CalendarSevice service;
	
	@GetMapping("/create")
	public void create() {
		//뷰 이동
	}

	@PostMapping("/create")
	public String create(CalendarTodoVO vo){
		
		service.register(vo);
		
		return "redirect:/fullcalendar-5.6.0/Calendar/list";
	}
	
	
	@GetMapping("/list")
	public void list(Model model) {

		model.addAttribute("list", service.list());
		
		log.info("list: " + model);
	}
	
	
	@PostMapping("/check")
	public String check(CalendarTodoVO vo, RedirectAttributes rttr) {
		log.info("check:" + vo);

		if (service.updateCheck(vo)>0) {
			rttr.addFlashAttribute("result", "success");
		}


		return "redirect:/fullcalendar-5.6.0/Calendar/list";
	}
	@PostMapping("/noncheck")
	public String noncheck(CalendarTodoVO vo, RedirectAttributes rttr) {
		log.info("noncheck:" + vo);

		if (service.updateNonCheck(vo)>0) {
			rttr.addFlashAttribute("result", "success");
		}


		return "redirect:/fullcalendar-5.6.0/Calendar/list";
	}
	
	@DeleteMapping("/delete")
	public String remove(CalendarTodoVO vo, RedirectAttributes rttr) {

		log.info("remove..." + vo.getT_num());
		
		service.delete(vo);

		return "redirect:/fullcalendar-5.6.0/Calendar/list";
		
	}

}
