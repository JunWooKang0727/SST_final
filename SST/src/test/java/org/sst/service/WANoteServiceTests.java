package org.sst.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.WANoteVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class WANoteServiceTests {
	@Setter(onMethod_ = { @Autowired })
	private WANoteService service;
	
//	@Test
//	public void testCreate(){
//		WANoteVO vo = new WANoteVO();
//		vo.setM_id("ggy");
//		vo.setW_title("ServiceTest");
//		vo.setW_question("ServiceTest");
//		vo.setW_answer("ServiceTest");
//		vo.setW_reason("ServiceTest");
//		vo.setW_tag1("ServiceTest");
//		vo.setW_tag2("ServiceTest");
//		
//		log.info(service.createWANote(vo));
//	}
	
//	@Test
//	public void testList() {
//		log.info(service.listWANote("ggy"));
//	}
	
//	@Test
//	public void testRead() {
//		log.info(service.readWANote("w4"));
//	}
	
//	@Test
//	public void testUpdate() {
//		WANoteVO vo = new WANoteVO();
//		vo.setW_num("w4");
//		vo.setW_title("ServiceTest2");
//		vo.setW_question("ServiceTest2");
//		vo.setW_answer("ServiceTest2");
//		vo.setW_reason("ServiceTest2");
//		vo.setW_tag1("ServiceTest2");
//		vo.setW_tag2("ServiceTest2");
//		
//		log.info(service.updateWANote(vo));
//	}
	
//	@Test
//	public void testDelete() {
//		log.info(service.deleteWANote("w4"));
//	}
}
