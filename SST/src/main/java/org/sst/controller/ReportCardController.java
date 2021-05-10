package org.sst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sst.domain.ReportCardVO;
import org.sst.service.ReportCardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
@RequestMapping("/reportcard/*")
@AllArgsConstructor
public class ReportCardController {
	
	private ReportCardService service;
	
	@GetMapping("/list/{m_id}")
	public void list(@PathVariable("m_id") String m_id,Model model) {
		model.addAttribute("reportcardList", service.listReportCard(m_id));
	}
	
	@GetMapping("/read/{rc_num}")
	public void read(@PathVariable("rc_num") String rc_num,Model model) {
		model.addAttribute("reportcard", service.readReportCard(rc_num));
	}
	
	@PostMapping("/create")
	public String create(ReportCardVO vo, RedirectAttributes rttr){
		service.createReportCard(vo);	
		return "redirect:/list/"+vo.getM_id();
	}
	
	@DeleteMapping("/delete/{rc_num}/{m_id}")
	public String delete(@PathVariable("rc_num") String rc_num,@PathVariable("m_id") String m_id){
		service.deleteReportCard(rc_num);
		return "redirect:/list/"+m_id;
	}
	

}
