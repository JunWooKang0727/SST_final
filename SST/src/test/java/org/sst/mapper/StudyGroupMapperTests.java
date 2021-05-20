package org.sst.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.GroupMemberVO;
import org.sst.domain.StudyGroupVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class StudyGroupMapperTests {
	@Setter(onMethod_ = @Autowired)
	private StudyGroupMapper mapper;
	
	/* 그룹 생성 테스트
	@Test
	public void testGroupInsert(){
		StudyGroupVO group = new StudyGroupVO("1","group1", "group1", "1", "1234", "group1", "5");
		mapper.groupInsert(group);
	}*/
	
	/* 그룹 멤버 추가 테스트
	 * 1. 그룹 생성할 때 생성한 사람 멤버에 추가할 때사용
	 * 2. 그룹 신청한 사람 승인받을 때 사용
	@Test
	public void testGroupMemberInsert(){
		GroupMemberVO gmem = new GroupMemberVO("gn22", "gn22", 1, 1, "member01");
		mapper.insertGroupMember(gmem);
	}*/
	
	// 그룹 1개 상세 조회 테스트 
	/*@Test
	public void testGroupDetailRead(){
		StudyGroupVO group = mapper.groupDetailReada("gn23");
		log.info(group);
	}
	*/
	/* 그룹 수정 테스트
	@Test
	public void testGroupUpdate(){
		StudyGroupVO group = mapper.groupDetailRead("gn23");
		group.setG_name("23232323");
		mapper.groupUpdate(group);
		log.info(group);
	}*/
	
	/* 그룹 삭제 테스트
	@Test
	public void testGroupDelete(){
		mapper.groupDelete("gn21");
	}*/
	
	@Test
	public void testMyGroupRead(){
		mapper.groupMakeRead("study0516").forEach(groups -> log.info(groups));
	}
	
}
