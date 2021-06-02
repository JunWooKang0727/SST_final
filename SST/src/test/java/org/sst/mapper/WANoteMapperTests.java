package org.sst.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class WANoteMapperTests {
	@Setter(onMethod_=@Autowired)
	private WANoteMapper mapper;

//	@Test
//	public void testCreate(){
//		WANoteVO vo = new WANoteVO();
//		vo.setM_id("ggy");
//		vo.setW_title("MapperTest");
//		vo.setW_question("MapperTest");
//		vo.setW_answer("MapperTest");
//		vo.setW_reason("MapperTest");
//		vo.setW_subject("MapperTest");
//		
//		log.info(mapper.createWANote(vo));
//	}
	
//	@Test
//	public void testList1() {
//		log.info(mapper.listWANote("ggy"));
//	}
	
	@Test
	public void testRead() {
		log.info(mapper.readWANote("108"));
	}
	
//	@Test
//	public void testUpdate() {
//		WANoteVO vo = new WANoteVO();
//		vo.setW_num("w2");
//		vo.setW_title("MapperTest2");
//		vo.setW_question("MapperTest2");
//		vo.setW_answer("MapperTest2");
//		vo.setW_reason("MapperTest2");
//		vo.setW_tag1("MapperTest2");
//		vo.setW_tag2("MapperTest2");
//		
//		log.info(mapper.updateWANote(vo));
//	}
	
//	@Test
//	public void testDelete() {
//		log.info(mapper.deleteWANote("w2"));
//	}
	
//	@Test
//	public void testList2() {
//		HashMap map = new HashMap<>();
//		map.put("m_id", "ggy");
//		map.put("cri", new Criteria(1,3));
//		map.put("typeArr", new Criteria(1,3).getTypeArr());
//		log.info(mapper.listWithPagingWANote(map));
//	}
	
//	@Test
//	public void testCount() {
//		HashMap map = new HashMap<>();
//		map.put("m_id", "ggy");
//		map.put("cri", new Criteria(1,3));
//		map.put("typeArr", new Criteria(1,3).getTypeArr());
//		log.info(mapper.getTotalCount(map));
//	}
	
//	  @Test
//	  public void testCreateWANotes() {
//
//	    for(int i = 0; i < 100; i++) {
//	    	WANoteVO vo = new WANoteVO();
//			vo.setM_id("test");
//			vo.setW_title("MapperTest");
//			vo.setW_question("MapperTest");
//			vo.setW_answer("MapperTest");
//			vo.setW_reason("MapperTest");
//			vo.setW_subject("MapperTest");
//			
//			log.info(mapper.createWANote(vo));
//
//	      }
//	    }//end for
	
//	@Test
//	public void testCreateTag() {
//		WAtagVO vo1 =mapper.readTag("안유진");
//		if(vo1==null){
//			WAtagVO vo2 =new WAtagVO();
//			vo2.setTg_name("안유진");
//			log.info(mapper.createTag(vo2));
//			log.info("result----------------------------------"+vo2.getTg_num());
//			HashMap map = new HashMap();
//			map.put("w_num", "335");
//			map.put("tg_num", vo2.getTg_num());
//			log.info("result(없을때)--------------------------------"+mapper.createWATag(map));
//		}else{
//			HashMap map = new HashMap();
//			map.put("w_num", "335");
//			map.put("tg_num", vo1.getTg_num());
//			log.info("result(있을때)--------------------------------"+mapper.createWATag(map));
//		}
//	}
//	@Test
//	public void testCountChart() {
//
//		log.info(mapper.countTagChart("ggy"));
//	}
	
//	@Test
//	public void testListWithTag() {
//		Criteria cri = new Criteria();
//		cri.setKeyword("파이썬");
//		log.info(mapper.listWithTagWANote(cri));
//		log.info(mapper.getTotalCountTag(cri));
//	}
	
//	@Test
//	public void testRecommend() {
//		log.info(mapper.recommendStudyGrop(mapper.countTagChart("test").get(0).get("TG_NAME").toString()));
////		mapper.recommendStudyGrop();
//	}
}
