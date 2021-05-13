package org.sst.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.StudyGroupVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class StudyGroupMapperTests {
	@Setter(onMethod_ = @Autowired)
	private StudyGroupMapper mapper;
	
	@Test
	public void testGroupInsert(){
		StudyGroupVO group = new StudyGroupVO("1","group1", "group1", "1", "1234", "group1", "5");
		mapper.groupInsert(group);
	}
	
	
	// 그룹 상세 조회 
	@Test
	public void testGroupDetailRead(){
		StudyGroupVO group = mapper.groupDetailRead("gn23");
		log.info(group);
	}
	
	@Test
	public void testGroupUpdate(){
		StudyGroupVO group = mapper.groupDetailRead("gn23");
		group.setG_name("23232323");
		mapper.groupUpdate(group);
		log.info(group);
	}
	
	@Test
	public void testGroupDelete(){
		mapper.groupDelete("gn21");
	}
}
