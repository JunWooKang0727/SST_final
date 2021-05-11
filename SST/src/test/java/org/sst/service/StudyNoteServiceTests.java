package org.sst.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.Criteria;
import org.sst.domain.StudyNoteVO;
import org.sst.mapper.StudyNoteTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Log4j
public class StudyNoteServiceTests {
	
	@Setter(onMethod_=@Autowired)
	private StudyNoteService service;
	
	
//	@Test
//	public void testInsert(){
//		
//		StudyNoteVO vo = new StudyNoteVO();
//		
//		vo.setSn_writer("dkstjdals");
//		vo.setSn_contents("rkdxortn");
//		vo.setSn_title("rkdwnsdn");
//		
//		service.register(vo);
//		
//	}
	
//	@Test
//	public void testList(){
//		
//		Criteria cri = new Criteria();
//		
//		service.getList(cri).forEach(stdno->log.info(stdno));
//		
//	}
	
//	@Test
//	public void testGet(){
//		log.info(service.read("sn_22"));
//	}
	
//	@Test
//	public void testUpdate(){
//		
//		StudyNoteVO vo = new StudyNoteVO();
//		
//		vo.setSn_title("안성민 수정");
//		vo.setSn_contents("강택수 수정");
//		vo.setSn_num("sn_22");
//		
//		log.info("update Row : "+service.update(vo));
//	}
	
//	@Test
//	public void testDelete(){
//		
//		log.info("delete Row : "+service.delete("sn_25"));
//		
//	}
	
/*	@Test
	public void testCount(){
		log.info("count row : " +service.getTotal(new Criteria()));
	}*/
}
