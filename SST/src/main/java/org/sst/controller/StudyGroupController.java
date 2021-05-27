package org.sst.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sst.domain.Criteria;
import org.sst.domain.Criteria2;
import org.sst.domain.GroupMemberVO;
import org.sst.domain.PageDTO;
import org.sst.domain.PageDTO2;
import org.sst.domain.StudyGroupVO;
import org.sst.service.StudyGroupService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/group/*")
@AllArgsConstructor
public class StudyGroupController {
	
	private StudyGroupService service;
	
	// 메인 페이지에서 회원이 생성한 그룹, 참여중인 그룹, 신청 대기중 그룹
	@GetMapping("/main")
	public void groupMainPage(Principal principal, Model model){
		log.info("[Group Home Page]");
		log.info("[get my group list]");
		log.info(principal.getName());
		model.addAttribute("mygrouplist", service.myGroupGet(principal.getName(), "1"));
		model.addAttribute("attendgroup", service.myAttendListGet(principal.getName()));
		model.addAttribute("waitlist", service.myWaitListGet(principal.getName()));
	}
	
	@GetMapping("/create")
	public void groupCreate(){
		log.info("[Group Create Page GET]");
	}
	
	@PostMapping("/create")
	public void groupCreate(StudyGroupVO group, Principal principal){
		log.info("[Group Create Page POST]" + group);
		service.groupCreate(group, principal.getName());
	}
	
	@GetMapping({"/read", "/update"})
	public void groupDetailRead(@RequestParam("g_num") String g_num, Model model){
		log.info("[Group Read / Update Page]");
		model.addAttribute("group", service.groupDetailGet(g_num));
		model.addAttribute("waitmember", service.memberListGet(g_num, "0"));
		model.addAttribute("memberlist", service.memberListGet(g_num, "1"));
		log.info(service.groupDetailGet(g_num));
		log.info(service.memberListGet(g_num, "0"));
	}
	
	@GetMapping("/selectdetail")
	public void groupSearchDetailRead(@RequestParam("g_num") String g_num,
			@ModelAttribute("cri") Criteria2 cri, Model model) {
		log.info("[Group search detail read]");
		model.addAttribute("group", service.groupDetailGet(g_num));
		
	}
	
	@PostMapping("/update")
	public String groupUpdate(StudyGroupVO group){
		log.info("[Group Update Page POST]");
		log.info(group.getG_num());
		log.info(service.groupModify(group));
		return "redirect:/group/read?g_num=" + group.getG_num();
	}
	
	@PostMapping("/remove")
	public String groupDetail(StudyGroupVO group){
		log.info("[Group Detail]");
		log.info(group.getG_num());
		service.groupRemove(group.getG_num());
		return "redirect:/group/main";
	}
	
	/*@GetMapping("/search")
	public void totalgroupRead(Model model){
		log.info("[Group Total Read]");
		model.addAttribute("totalgroup", service.totalGroupGet());
	}*/
	                                                        
	@GetMapping("/search")
	public void totalgroupRead(Criteria2 cri, Model model){
		log.info("[Group Total Read]");
		model.addAttribute("totalgroup", service.totalGroupGet(cri));
		model.addAttribute("pageMaker", new PageDTO2(cri, service.getTotal(cri)));
	}
	
	@PostMapping("/join")
	public String joinGroup(Principal principal, @RequestParam("g_num") String g_num){
		log.info("[Group Join]");
		GroupMemberVO gm = new GroupMemberVO();
		gm.setG_num(g_num);
		gm.setM_id(principal.getName());
		gm.setP_grant(3);
		gm.setGm_status("0");
		service.joinGroup(gm);
		return "redirect:/group/search";
	}
	
	@ResponseBody
	@PostMapping("/accept")
	public void joinGroup(@RequestParam("g_num") String g_num, 
			@RequestParam("m_id") String m_id, Model model){
		log.info("[Group member Accept]");
		service.groupmemAccept(g_num, m_id);
		// model.addAttribute("group", service.groupDetailGet(g_num));
		model.addAttribute("waitmember", service.memberListGet(g_num, "0"));
		model.addAttribute("memberlist", service.memberListGet(g_num, "1"));
	}
	
	@ResponseBody
	@PostMapping("/deny")
	public void denyGroup(@RequestParam("g_num") String g_num, @RequestParam("m_id") String m_id,
			Model model){
		log.info("[Group member Deny]");
		service.groupmemDeny(g_num, m_id);
		// model.addAttribute("group", service.groupDetailGet(g_num));
		model.addAttribute("waitmember", service.memberListGet(g_num, "0"));
		model.addAttribute("memberlist", service.memberListGet(g_num, "1"));
	}
	
	@ResponseBody
	@PostMapping("/authupdate")
	public void updateAuth(@RequestParam("g_num") String g_num, 
			@RequestParam("m_id") String m_id, @RequestParam("p_grant") String p_grant
			, Model model){
		log.info("[Group member auth Update]");
		model.addAttribute("waitmember", service.memberListGet(g_num, "0"));
		model.addAttribute("memberlist", service.memberListGet(g_num, "1"));
	}
	
	@ResponseBody
	@PostMapping("/memdelete")
	public void deleteMem(@RequestParam("g_num") String g_num, @RequestParam("m_id") String m_id){
		log.info("[Group member auth Update]");
		
	}

}
