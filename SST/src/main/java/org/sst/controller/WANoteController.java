package org.sst.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sst.domain.Criteria;
import org.sst.domain.PageDTO;
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
}