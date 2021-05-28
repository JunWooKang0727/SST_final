package org.sst.controller;

import java.security.Principal;

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
@RequestMapping("/calendar/*")
@AllArgsConstructor
public class CalendarController {
	
	private CalendarSevice service;
	
	@GetMapping("/create")
	public void create() {
		//뷰 이동
	}

	@PostMapping("/create")
	public String create(CalendarTodoVO vo,Principal principal){
		String id = principal.getName();
		service.register(vo,id);
		
		return "redirect:/calendar/list";
	}
	
	
	@GetMapping("/list")
	public void list(Model model, Principal principal) {
		String id = principal.getName();
		model.addAttribute("list", service.list(id));
		
		log.info("list: " + model);
	}
	
	
	@GetMapping("/check")
	public String check(CalendarTodoVO vo, RedirectAttributes rttr,Principal principal) {
		log.info("check:" + vo);
		String id = principal.getName();
		service.updateCheck(vo,id);
		if (service.updateCheck(vo,id)>0) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/calendar/list";
	}
	@GetMapping("/noncheck")
	public String noncheck(CalendarTodoVO vo, RedirectAttributes rttr,Principal principal) {
		log.info("noncheck:" + vo);
		String id = principal.getName();
		service.updateNonCheck(vo,id);
		if (service.updateNonCheck(vo,id)>0) {
			rttr.addFlashAttribute("result", "success");
		}


		return "redirect:/calendar/list";
	}
	
	@GetMapping("/delete")
	public String remove(CalendarTodoVO vo, RedirectAttributes rttr,Principal principal) {
		String id = principal.getName();
		log.info("remove..." + vo.getT_num());
		
		service.delete(vo,id);

		return "redirect:/calendar/list";
		
	}

}
