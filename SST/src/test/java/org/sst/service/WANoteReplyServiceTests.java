package org.sst.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.Criteria;
import org.sst.domain.WANoteReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class WANoteReplyServiceTests {
	@Setter(onMethod_ = { @Autowired })
	private WAnoteReplyService service;
	
//	@Test
//	public void testCreate(){
//		WANoteReplyVO vo = new WANoteReplyVO();
//		vo.setM_id("ggy");
//		vo.setW_num("350");
//		vo.setWr_contents("빨간맛");
//		vo.setWr_date("2019-12-15");
//		log.info(service.createWANoteReply(vo));
//	}
	
//	@Test
//	public void testList(){
//		log.info(service.listWANoteReply(new Criteria(1,2),"350"));
//	}
	
//	@Test
//	public void testList(){
//		log.info(service.deleteWANoteReply("3"));
//	}
	
//	@Test
//	public void testUpdate(){
//		WANoteReplyVO vo = new WANoteReplyVO();
//		vo.setWr_num("5");
//
//		vo.setWr_contents("에스파는 나야");
//		log.info(service.updateWANoteReply(vo));
//	}
}
