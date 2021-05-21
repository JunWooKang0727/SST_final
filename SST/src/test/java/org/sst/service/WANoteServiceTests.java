package org.sst.service;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.Criteria;
import org.sst.domain.WANoteVO;
import org.sst.domain.WAtagVO;

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
//	public void testList2() {
//		HashMap map = new HashMap();
//		Criteria cri = new Criteria(1,10);
//		cri.setType("T");
//		cri.setKeyword("h");
//		map.put("m_id", "ggy");
//		map.put("cri", cri);
//		map.put("typeArr", cri.getTypeArr());
//		log.info("result---------------"+service.getTotalCount(map));
//		log.info(service.listWithPagingWANote(map));
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
	
//	@Test
//	public void testList() {
//		log.info(service.listAllTag("자바"));
//	}
	
//	
//	@Test
//	public void testCreateTag() {
//		WAtagVO vo = new WAtagVO();
//		vo.setTg_name("메모리");
//		WAtagVO vo1 =service.readTag(vo);
//
//		if(vo1==null){
//			log.info(service.createTag(vo));
//			log.info("result----------------------------------"+vo.getTg_num());
//			HashMap map = new HashMap();
//			map.put("w_num", "335");
//			map.put("tg_num", vo.getTg_num());
//			log.info("result(없을때)--------------------------------"+service.createWATag(map));
//		}else{
//			HashMap map = new HashMap();
//			map.put("w_num", "335");
//			map.put("tg_num", vo1.getTg_num());
//			log.info("result(있을때)--------------------------------"+service.createWATag(map));
//		}
//	}
}
