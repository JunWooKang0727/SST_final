package org.sst.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.Criteria;
import org.sst.domain.Criteria2;
import org.sst.domain.GroupMemberVO;
import org.sst.domain.StudyGroupVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class StudyGroupServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private StudyGroupService service;
	
	/*@Test
	public void groupCreateService(){
		StudyGroupVO group = new StudyGroupVO();
		group.setG_name("study0516");
		group.setG_content("study0516");
		group.setG_secreat("study0516");
		group.setG_category("study0516");
		group.setG_memnum("study0516");
		group.setG_passwd("study0516");
		service.groupCreate(group, "member01");
	}
	*/
	/*@Test
	public void groupDetailReadService(){
		log.info(service.groupDetailGet("gn23"));
	}*/
	
	/*@Test
	public void groupUpdateService(){
		StudyGroupVO group = service.groupDetailGet("gn23");
		group.setG_name("group0512");
		service.groupModify(group);
	}*/
	
	/*@Test
	public void groupDeleteService(){
		service.groupRemove("gn23");
	}*/
	
	/*@Test
	public void mygroupReadTest(){
		service.myGroupGet("study0516");
	}*/
	
	/*@Test
	public void totalgroupReadTest(){
		service.totalGroupGet().forEach(groups -> log.info(groups));
	}*/
	
	/*@Test
	public void testGetList(){
		Criteria2 cri = new Criteria2();
		cri.setKeyword("영어");
		cri.setType("T");
		cri.setPageNum(1);
		service.totalGroupGet(cri).forEach(groups -> log.info(groups));
	}*/
	
	/*@Test
	public void joinGroupoTest(){
		GroupMemberVO gm = new GroupMemberVO();
		gm.setG_num("gn9");
		gm.setP_grant(3);
		gm.setGm_status("0");
		gm.setM_id("kakaocon");
		service.joinGroup(gm);
	}*/
	
	/*@Test
	public void waitinglistTest(){
		List<GroupMemberVO> list = service.memberListGet("gn9", "0");
		list.forEach(group -> log.info(group));
	}*/

	/*@Test
	public void accgroupmemTest(){
		service.groupmemAccept("gn9", "sleep");
	}*/
	
	/*@Test
	public void denyGroupmemTest(){
		service.groupmemDeny("gn9", "javastudy");
	}*/
	
	/*@Test
	public void groupAttendReadTest(){
		service.myAttendListGet("sleep");
	}*/
	
	/*@Test
	public void groupWaitTest(){
		service.myWaitListGet("sleep");
	}*/
	
	/*@Test
	public void grouptotal(){
		service.getMemTotal("gn9");
	}*/
	
	/*@Test
	public void memAuthUpdateTest(){
		service.groupmemAuthUpdate("2", "gn9", "BA01");
	}*/
	
	@Test
	public void memDelTest(){
		service.groupmemDel("gn9", "BA01");
	}

}
