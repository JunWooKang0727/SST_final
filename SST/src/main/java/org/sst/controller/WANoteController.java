package org.sst.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sst.domain.Criteria;
import org.sst.domain.PageDTO;
import org.sst.domain.WANoteVO;
import org.sst.domain.WAtagVO;
import org.sst.service.WANoteService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/wanote/*")
@AllArgsConstructor
public class WANoteController {
	private WANoteService service;
	
	@GetMapping("/create")
	public void create() {
	}
	
	@PostMapping("/create")
	public String create(WANoteVO vo,RedirectAttributes rttr) {
		String w_num=service.createWANote(vo);
		
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
	
	@GetMapping("/list")
	public void list(Criteria cri,String m_id, Model model) {
		HashMap map = new HashMap();
		map.put("m_id", m_id);
		map.put("cri", cri);
		map.put("typeArr", cri.getTypeArr());
		model.addAttribute("list", service.listWithPagingWANote(map));
		int total = service.getTotalCount(map);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping({ "/read", "/update" })
	public void get(@RequestParam("w_num") String w_num, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/get or modify");
		model.addAttribute("wanote", service.readWANote(w_num));
	}
}
