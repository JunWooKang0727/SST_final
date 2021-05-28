package org.sst.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sst.domain.Criteria;
import org.sst.domain.GroupMemberVO;
import org.sst.domain.MemberVO;
import org.sst.domain.PageDTO;
import org.sst.domain.StudyNoteVO;
import org.sst.service.GroupMemberService;
import org.sst.service.MemberService;
import org.sst.service.StudyNoteService;


import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/studynote/*")
@AllArgsConstructor
public class StudyNoteController {

	private StudyNoteService service;

	private MemberService m_service;
	
	private GroupMemberService gm_service;
	
	@GetMapping("/create")
	public void create(String g_num, Model model) {
		//뷰 이동...--
		model.addAttribute("g_num",g_num);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(StudyNoteVO vo,RedirectAttributes rttr,Principal principal){
		
		MemberVO mvo =  m_service.memberGet(principal.getName());

		GroupMemberVO gmvo = new GroupMemberVO();
		gmvo.setM_id(principal.getName());
		gmvo.setG_num(vo.getG_num());
		
		//gm_service.gmRead(gmvo).getGm_num();
		
		vo.setGm_num(gm_service.gmRead(gmvo).getGm_num());
		
		service.register(vo);
		log.info("create : "+vo.getG_num());
		rttr.addAttribute("g_num", vo.getG_num());
		
		return "redirect:/studynote/list";
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Criteria cri, Model model,HttpServletRequest request) {
		//cri.setG_num(g_num1);
		log.info("list: " + cri);
		HttpSession session=request.getSession();
		String gn = "";
		if(cri.getG_num() != null){
			
			
			session.setAttribute("g_num", cri.getG_num());
			gn = cri.getG_num();
		}else{
			gn = session.getAttribute("g_num").toString();
		}
		
		cri.setG_num(gn);
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, 123));
		model.addAttribute("cri", cri);
		int total = service.getTotal(cri);

		log.info("total: " + total);

		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping({ "/read", "/update" })
    public void get(@RequestParam("sn_num") String sn_num, @ModelAttribute("cri") Criteria cri, Model model) {

        log.info("/get or modify");
        model.addAttribute("studynote", service.read(sn_num));
    }
	
	@PostMapping("/update")
	public String modify(StudyNoteVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify:" + vo);

		if (service.update(vo)>0) {
			rttr.addFlashAttribute("result", "success");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("g_num", vo.getG_num());
		return "redirect:/board/list";
	}
	
	@PostMapping("/delete")
	public String remove(@RequestParam("sn_num") String sn_num, Criteria cri, RedirectAttributes rttr) {

		log.info("remove..." + sn_num);
		//rttr.addAttribute("g_num", vo.getG_num());
		//List<BoardAttachVO> attachList = service.getAttachList(bno);f
		service.delete(sn_num);
/*		if (service.remove(bno)) {

			// delete Attach Files
			deleteFiles(attachList);

			rttr.addFlashAttribute("result", "success");
		}*/
		return "redirect:/board/list";
		//return "redirect:/board/list" + cri.getListLink();
	}

}
