package org.sst.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sst.domain.LicenseScoreVO;
import org.sst.domain.LicenseTestVO;
import org.sst.domain.ReportCardVO;
import org.sst.domain.SchoolScoreVO;
import org.sst.domain.SchoolTestVO;
import org.sst.domain.StudyGroupVO;
import org.sst.domain.WANoteReplyVO;
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
	@GetMapping("/list")
	public void list(@RequestParam("m_id") String m_id, Model model) {
		model.addAttribute("reportcardList", service.listReportCard(m_id));
	}

	@GetMapping("/read")
	public String read(@RequestParam("rc_num") String rc_num, Model model) {
		ReportCardVO vo = service.readReportCard(rc_num);
		model.addAttribute("reportcard", vo);
		model.addAttribute("rc_num", rc_num);
		if (vo.getRc_subtype().equals("학교성적")) {
			List<SchoolTestVO> list = service.listSchoolTest(rc_num);
			if (list.size() < 1) {
				return "/reportcard/createSchoolTest";
			} else {
				model.addAttribute("schoolTestList", list);
				return "/reportcard/readSchoolReportCard";
			}
		} else {
			List<LicenseTestVO> list = service.listLicenseTest(rc_num);
			if (list.size() < 1) {
				return "/reportcard/createLicenseTest";
			} else {
				model.addAttribute("licenseTestList", list);

				log.info(service.listLicenseTest(rc_num));

				return "/reportcard/readLicenseReportCard";
			}
		}
	}

	@GetMapping("/create")
	public void create() {
	}

	@PostMapping("/create")
	public String create(ReportCardVO vo, RedirectAttributes rttr) {
		if (vo.getRc_type().endsWith("학교")) {
			vo.setRc_subtype("학교성적");
		}
		service.createReportCard(vo);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/list?m_id=" + vo.getM_id();
	}

	@GetMapping("/update")
	public void update(@RequestParam("rc_num") String rc_num, Model model) {
		ReportCardVO vo = service.readReportCard(rc_num);
		model.addAttribute("reportcard", vo);
	}
	
	@PostMapping("/update")
	public String update(ReportCardVO vo, RedirectAttributes rttr) {
		service.updateReportCard(vo);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/list?m_id=" + vo.getM_id();
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("rc_num") String rc_num, @RequestParam("rc_subtype") String rc_subtype,
			RedirectAttributes rttr) {
		if (rc_subtype.equals("학교성적")) {
			service.listSchoolTest(rc_num).forEach(test -> {
				service.deleteSchoolTestScore(test.getSt_num());
				service.deleteSchoolTest(test.getSt_num());
			});
		} else {
			service.listLicenseTest(rc_num).forEach(test -> {
				service.deleteLicenseTestScore(test.getLt_num());
				service.deleteLicenseTest(test.getLt_num());
			});
		}
		service.deleteReportCard(rc_num);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/list?m_id=ggy";
	}

	
	
	
	// SchoolTest
	@GetMapping("/schooltest/create")
	public String createSchoolTest(@RequestParam("rc_num") String rc_num, Model model) {
		model.addAttribute("rc_num", rc_num);
		return "/reportcard/createSchoolTest";
	}

	@PostMapping("/schooltest/create")
	public String createSchoolTest(SchoolTestVO vo, RedirectAttributes rttr) {
		String st_num = service.createSchoolTest(vo);
		vo.getScorelist().forEach(score -> {
			score.setSt_num(st_num);
			service.createSchoolScore(score);
		});
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/read?rc_num=" + vo.getRc_num();
	}

	@GetMapping("/schooltest/delete")
	public String deleteSchoolTest(@RequestParam("rc_num") String rc_num, @RequestParam("st_num") String st_num,
			RedirectAttributes rttr) {
		service.deleteSchoolTestScore(st_num);
		service.deleteSchoolTest(st_num);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/read?rc_num=" + rc_num;
	}

	@GetMapping("/schooltest/update")
	public String updateSchoolTest(@RequestParam("st_num") String st_num, Model model) {
		model.addAttribute("st", service.readSchoolTest(st_num));
		return "/reportcard/updateSchoolTest";
	}

	@PostMapping("/schooltest/update")
	public String updateSchoolTest(SchoolTestVO vo, RedirectAttributes rttr) {
		service.updateSchoolTest(vo);
		vo.getScorelist().forEach(score -> {
			service.updateSchoolScore(score);
		});
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/read?rc_num=" + vo.getRc_num();
	}

	// SchoolScore
	@PostMapping("/schoolscore/create")
	public String createSchoolScore(SchoolScoreVO vo, RedirectAttributes rttr) {
		if (service.createSchoolScore(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/reportcard/schooltest/update?st_num=" + vo.getSt_num();
	}

	@GetMapping("/schoolscore/delete")
	public String deleteSchoolScore(@RequestParam("ss_num") String ss_num, @RequestParam("st_num") String st_num,
			RedirectAttributes rttr) {
		if (service.deleteSchoolScore(ss_num)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/reportcard/schooltest/update?st_num=" + st_num;
	}

	
	// LicenseTest
	@GetMapping("/licensetest/create")
	public String createLicenseTest(@RequestParam("rc_num") String rc_num, Model model) {
		model.addAttribute("rc_num", rc_num);
		return "/reportcard/createLicenseTest";
	}

	@PostMapping("/licensetest/create")
	public String createLicenseTest(LicenseTestVO vo, RedirectAttributes rttr) {
		String lt_num = service.createLicenseTest(vo);
		vo.getScorelist().forEach(score -> {
			score.setLt_num(lt_num);
			service.createLicenseScore(score);
		});
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/read?rc_num=" + vo.getRc_num();
	}

	@GetMapping("/licensetest/delete")
	public String deleteLicenseTest(@RequestParam("rc_num") String rc_num, @RequestParam("test_num") String lt_num,
			RedirectAttributes rttr) {
		service.deleteLicenseTestScore(lt_num);
		service.deleteLicenseTest(lt_num);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/read?rc_num=" + rc_num;
	}

	@GetMapping("/licensetest/update")
	public String updateLicenseTest(@RequestParam("lt_num") String lt_num, Model model) {
		model.addAttribute("test", service.readLicenseTest(lt_num));
		return "/reportcard/updateLicenseTest";
	}

	@PostMapping("/licensetest/update")
	public String updateLicenseTest(LicenseTestVO vo, RedirectAttributes rttr) {
		service.updateLicenseTest(vo);
		vo.getScorelist().forEach(score -> {
			service.updateLicenseScore(score);
		});
		rttr.addFlashAttribute("result", "success");
		return "redirect:/reportcard/read?rc_num=" + vo.getRc_num();
	}

	// LicenseScore
	@PostMapping("/licensescore/create")
	public String createLicenseScore(LicenseScoreVO vo, RedirectAttributes rttr) {
		if (service.createLicenseScore(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/reportcard/licensetest/update?lt_num=" + vo.getLt_num();
	}

	@GetMapping("/licensescore/delete")
	public String deleteLicenseScore(@RequestParam("ls_num") String ls_num, @RequestParam("lt_num") String lt_num,
			RedirectAttributes rttr) {
		if (service.deleteLicenseScore(ls_num)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/reportcard/licensetest/update?lt_num=" + lt_num;
	}
	
	
	@GetMapping(value = "/recommendLicenseTest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<StudyGroupVO>> recommendLicenseTest(ReportCardVO vo) {
		return new ResponseEntity<>(service.recommendLicenseTest(vo),HttpStatus.OK);

	}
	
	@GetMapping(value = "/recommendSchoolTest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<StudyGroupVO>> recommendSchoolTest(ReportCardVO vo) {
		HashMap map = service.worstSubject(vo.getRc_num());
		map.put("rc_type", vo.getRc_type());
		return new ResponseEntity<>(service.recommendSchoolTest(map),HttpStatus.OK);

	}
	 
}
