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
import org.sst.domain.SchoolScoreVO;
import org.sst.domain.SchoolTestVO;
import org.sst.service.ReportCardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/reportcard/*")
@AllArgsConstructor
public class ReportCardController {

	private ReportCardService service;

	// ReportCard
	@GetMapping("/list/{m_id}")
	public void list(@PathVariable("m_id") String m_id, Model model) {
		model.addAttribute("reportcardList", service.listReportCard(m_id));
	}

	@GetMapping("/read/{rc_num}")
	public void read(@PathVariable("rc_num") String rc_num, Model model) {
		ReportCardVO vo = service.readReportCard(rc_num);
		model.addAttribute("reportcard", vo);
		if (vo.getRc_subtype().equals("학교성적")) {
			model.addAttribute("SchoolTest", service.listSchoolTest(rc_num));
		}
	}

	@PostMapping("/create")
	public String create(ReportCardVO vo, RedirectAttributes rttr) {
		service.createReportCard(vo);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/list/" + vo.getM_id();
	}

	@DeleteMapping("/delete/{rc_num}/{rc_subtype}/{m_id}")
	public String delete(@PathVariable("rc_num") String rc_num,@PathVariable("rc_subtype") String rc_subtype,
			@PathVariable("m_id") String m_id,RedirectAttributes rttr){
		if (rc_subtype.equals("학교성적")) {
			service.listSchoolTest(rc_num).forEach(test ->{
			service.deleteSchoolTestScore(test.getSt_num());
			service.deleteSchoolTest(test.getSt_num());	
			});
		}
		service.deleteReportCard(rc_num);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/list/"+m_id;
	}

	// SchoolTest
	@PostMapping("/schooltest/create")
	public String createSchoolTest(SchoolTestVO vo, RedirectAttributes rttr) {
		String st_num = service.createSchoolTest(vo);
		vo.getScorelist().forEach(score -> {
			score.setSt_num(st_num);
			service.createSchoolScore(score);
		});
		rttr.addFlashAttribute("result", "success");
		return "redirect:/read/" + vo.getRc_num();
	}

	@GetMapping("/schooltest/read/{st_num}")
	public void readSchoolTest(@PathVariable("st_num") String st_num, Model model) {
		model.addAttribute("schooltest", service.readSchoolTest(st_num));
	}

	@DeleteMapping("/schooltest/delete/{rc_num}/{st_num}")
	public String deleteSchoolTest(@PathVariable("rc_num") String rc_num, @PathVariable("st_num") String st_num,
			RedirectAttributes rttr) {
		service.deleteSchoolTestScore(st_num);
		service.deleteSchoolTest(st_num);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/read/" + rc_num;
	}

	@PostMapping("/schooltest/update")
	public String updateSchoolTest(SchoolTestVO vo, RedirectAttributes rttr) {
		service.updateSchoolTest(vo);
		vo.getScorelist().forEach(score -> {
			service.updateSchoolScore(score);
		});
		rttr.addFlashAttribute("result", "success");
		return "redirect:/schooltest/read/" + vo.getSt_num();
	}

	// SchoolScore
	@PostMapping("/schoolscore/create")
	public String createSchoolScore(SchoolScoreVO vo, RedirectAttributes rttr) {
		if(service.createSchoolScore(vo)){
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/schooltest/read/" + vo.getSt_num();
	}

	@DeleteMapping("/schoolscore/delete/{ss_num}/{st_num}")
	public String deleteSchoolScore(@PathVariable("ss_num") String ss_num, @PathVariable("st_num") String st_num,
			RedirectAttributes rttr) {
		if(service.deleteSchoolScore(ss_num)){
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/list/" + st_num;
	}

}
