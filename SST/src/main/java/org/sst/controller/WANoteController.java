package org.sst.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sst.domain.Criteria;
import org.sst.domain.PageDTO;
import org.sst.domain.StudyGroupVO;
import org.sst.domain.WANoteVO;
import org.sst.domain.WAtagVO;
import org.sst.domain.WanoteAttachVO;
import org.sst.service.WANoteService;
import org.sst.service.WanoteAttachService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/wanote/*")
@AllArgsConstructor
public class WANoteController {
	private WANoteService service;
	private WanoteAttachService attachService;

	@GetMapping("/create")
	public void create() {
	}
	@GetMapping("/home2")
	public void home2() {
	}
	
	@Transactional
	@PostMapping("/create")
	public String create(WANoteVO vo, RedirectAttributes rttr) {
		vo.setW_question(vo.getW_question().replace("\r\n","<br>"));
		vo.setW_answer(vo.getW_answer().replace("\r\n","<br>"));
		String w_num = service.createWANote(vo);
		if (vo.getAttachList() != null) {
			vo.getAttachList().forEach(file -> {
				file.setW_num(w_num);
				attachService.insert(file);
			});
		}
		if (vo.getTaglist() != null) {
			vo.getTaglist().forEach(tag -> {
				if (service.readTag(tag) == null) {
					String tg_num = service.createTag(tag);
					HashMap map = new HashMap();
					map.put("w_num", w_num);
					map.put("tg_num", tg_num);
					log.info("result(없을때)-----------d---------------------" + service.createWATag(map));
				} else {
					WAtagVO vo1 = service.readTag(tag);
					HashMap map = new HashMap();
					map.put("w_num", w_num);
					map.put("tg_num", vo1.getTg_num());
					log.info("result(있을때)--------------------------------" + service.createWATag(map));
					log.info(tag.getTg_num());
				}
			});
		}

		rttr.addFlashAttribute("result", "success");
		return "redirect:/wanote/mylist?m_id=" + vo.getM_id();
	}
	
	@Transactional
	@PostMapping("/update")
	public String update(WANoteVO vo,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		vo.setW_question(vo.getW_question().replace("\r\n","<br>"));
		vo.setW_answer(vo.getW_answer().replace("\r\n","<br>"));
		
		String w_num = vo.getW_num();
		service.deleteAllWaTag(w_num);
		attachService.deleteAll(w_num);
		service.updateWANote(vo);
		if (vo.getAttachList() != null) {
			vo.getAttachList().forEach(file -> {
				file.setW_num(w_num);
				attachService.insert(file);
			});
		}
		if (vo.getTaglist() != null) {
			vo.getTaglist().forEach(tag -> {
				if (service.readTag(tag) == null) {
					String tg_num = service.createTag(tag);
					HashMap map = new HashMap();
					map.put("w_num", w_num);
					map.put("tg_num", tg_num);
					log.info("result(없을때)--------------------------------" + service.createWATag(map));
				} else {
					WAtagVO vo1 = service.readTag(tag);
					HashMap map = new HashMap();
					map.put("w_num", w_num);
					map.put("tg_num", vo1.getTg_num());
					log.info("result(있을때)--------------------------------" + service.createWATag(map));
					log.info(tag.getTg_num());
				}
			});
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("result", "success");
		return "redirect:/wanote/read?w_num=" + vo.getW_num();
	}
	
	@Transactional
	@PostMapping("/delete")
	public String delete(@RequestParam("w_num") String w_num,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		service.deleteAllWaTag(w_num);
		attachService.deleteAll(w_num);
		service.deleteWANote(w_num);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/wanote/mylist"+ cri.getListLink2();
	}

	@GetMapping("/mylist")
	public void mylist(Criteria cri, Model model) {
		int total = 0;
		if (cri.getType() != null) {
			if (cri.getType().equals("Tag")) {
				model.addAttribute("list", service.listWithTagWANote(cri));
				total = service.getTotalCountTag(cri);
			} else {
				model.addAttribute("list", service.listWithPagingWANote(cri));
				total = service.getTotalCount(cri);
			}
		} else {
			model.addAttribute("list", service.listWithPagingWANote(cri));
			total = service.getTotalCount(cri);
		}

		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		int total = 0;
		if (cri.getType() != null) {
			if (cri.getType().equals("Tag")) {
				model.addAttribute("list", service.listWithTagWANote(cri));
				total = service.getTotalCountTag(cri);
			} else {
				model.addAttribute("list", service.listWithPagingWANote(cri));
				total = service.getTotalCount(cri);
			}
		} else {
			model.addAttribute("list", service.listWithPagingWANote(cri));
			total = service.getTotalCount(cri);
		}

		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	@GetMapping("/read")
	public void get(@RequestParam("w_num") String w_num, @ModelAttribute("cri") Criteria cri, Model model) {
		WANoteVO vo = service.readWANote(w_num);
		model.addAttribute("wanote", service.readWANote(w_num));
	}
	
	@GetMapping("/update" )
	public void update(@RequestParam("w_num") String w_num, @ModelAttribute("cri") Criteria cri, Model model) {
		WANoteVO vo = service.readWANote(w_num);
		vo.setW_question(vo.getW_question().replace("<br>","\r\n"));
		vo.setW_answer(vo.getW_answer().replace("<br>","\r\n"));
		model.addAttribute("wanote", vo);
	}

	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<WanoteAttachVO>> getAttachList(String w_num) {

		return new ResponseEntity<>(attachService.list(w_num), HttpStatus.OK);
	}

	@GetMapping(value = "/countTagChart/{m_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<HashMap>> countTagChart(@PathVariable("m_id") String m_id) {
		return new ResponseEntity<>(service.countTagChart(m_id), HttpStatus.OK);
	}

	@GetMapping(value = "/countReasonChart/{m_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<HashMap>> countReasonChart(@PathVariable("m_id") String m_id) {
		return new ResponseEntity<>(service.countReasonChart(m_id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/recommendStudyGroup/{m_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<StudyGroupVO>> recommendStudyGroup(@PathVariable("m_id") String m_id) {
		// log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+service.countTagChart(m_id).get(0).get("TG_NAME").toString());

		String tg_name ="";
		for(int i=0; i<service.countTagChart(m_id).size();i++){
			if (service.recommendStudyGrop(service.countTagChart(m_id).get(i).get("TG_NAME").toString()).size() != 0) {
				tg_name=service.countTagChart(m_id).get(i).get("TG_NAME").toString();
				break;
			}
		}

		return new ResponseEntity<>(service.recommendStudyGrop(tg_name),HttpStatus.OK);

	}
}
