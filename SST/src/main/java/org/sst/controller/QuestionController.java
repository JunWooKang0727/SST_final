package org.sst.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sst.domain.Criteria;
import org.sst.domain.PageDTO;
import org.sst.domain.QuestionVO;
import org.sst.service.QuestionService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/question/*")
@AllArgsConstructor
public class QuestionController {

	private QuestionService service;

	@GetMapping("/create")
	public void create() {
		//뷰 이동...--
	}

	@PostMapping("/create")
	public String create(QuestionVO vo){
		
		service.register(vo);
		
		return "redirect:/question/list";
	}
	
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {

		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));
		// model.addAttribute("pageMaker", new PageDTO(cri, 123));

		int total = service.getTotal(cri);

		log.info("total: " + total);

		model.addAttribute("pageMaker", new PageDTO(cri, total));

	}
	
	@GetMapping({ "/read", "/update" })
    public void get(@RequestParam("q_num") String q_num, @ModelAttribute("cri") Criteria cri, Model model) {

        log.info("/get or modify");
        model.addAttribute("question", service.read(q_num));
    }
	
	@PostMapping("/update")
public String modify(QuestionVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify:" + vo);

		if (service.update(vo)>0) {
			rttr.addFlashAttribute("result", "success");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/question/list";
	}
	
	@PostMapping("/delete")
	public String remove(@RequestParam("q_num") String q_num, Criteria cri, RedirectAttributes rttr) {

		log.info("remove..." + q_num);

		//List<BoardAttachVO> attachList = service.getAttachList(bno);f
		service.delete(q_num);
/*		if (service.remove(bno)) {

			// delete Attach Files
			deleteFiles(attachList);

			rttr.addFlashAttribute("result", "success");
		}*/
		return "redirect:/question/list";
		//return "redirect:/board/list" + cri.getListLink();
	}

}
