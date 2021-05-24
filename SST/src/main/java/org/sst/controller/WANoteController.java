package org.sst.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	
	@PostMapping("/create")
	public String create(WANoteVO vo,RedirectAttributes rttr) {
		String w_num=service.createWANote(vo);
		vo.getAttachList().forEach(file->{
			file.setW_num(w_num);
			attachService.insert(file);
		});;
		vo.getTaglist().forEach(tag->{
			if(service.readTag(tag)==null){
				String tg_num = service.createTag(tag); 
				HashMap map = new HashMap();
				map.put("w_num", w_num);
				map.put("tg_num", tg_num);
				log.info("result(없을때)--------------------------------"+service.createWATag(map));
			}else{
				WAtagVO vo1 = service.readTag(tag);
				HashMap map = new HashMap();
				map.put("w_num", w_num);
				map.put("tg_num", vo1.getTg_num());
				log.info("result(있을때)--------------------------------"+service.createWATag(map));
				log.info(tag.getTg_num());
			}
		});
		
		
		rttr.addFlashAttribute("result", "success");
		return "redirect:/wanote/list?m_id=" + vo.getM_id();
	}
	
	@GetMapping("/mylist")
	public void mylist(Criteria cri, Model model) {
 
		model.addAttribute("list", service.listWithPagingWANote(cri));
		int total = service.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		model.addAttribute("list", service.listWithPagingWANote(cri));
		int total = service.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping({ "/read", "/update" })
	public void get(@RequestParam("w_num") String w_num, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/get or modify");
		model.addAttribute("wanote", service.readWANote(w_num));
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
}
