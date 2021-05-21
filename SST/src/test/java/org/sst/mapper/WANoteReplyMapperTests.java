package org.sst.mapper;

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
public class WANoteReplyMapperTests {
	@Setter(onMethod_=@Autowired)
	private WANoteReplyMapper mapper;
	
//	@Test
//	public void testCreate(){
//		WANoteReplyVO vo = new WANoteReplyVO();
//		vo.setM_id("ggy");
//		vo.setW_num("350");
//		vo.setWr_contents("소녀시대");
//		vo.setWr_date("2019-12-15");
//		log.info(mapper.createWANoteReply(vo));
//	}
	
//	@Test
//	public void testList(){
//		log.info(mapper.listWANoteReply(new Criteria(1,2),"350"));
//	}
	
//	@Test
//	public void testList(){
//		log.info(mapper.deleteWANoteReply("3"));
//	}
	
//	@Test
//	public void testUpdate(){
//		WANoteReplyVO vo = new WANoteReplyVO();
//		vo.setWr_num("2");
//
//		vo.setWr_contents("에바싸바라고");
//		log.info(mapper.updateWANoteReply(vo));
//	}

}
