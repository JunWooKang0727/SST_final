package org.sst.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.StudyGroupVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class StudyGroupServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private StudyGroupService service;
	
	@Test
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
}
