package org.sst.mapper;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.Criteria;
import org.sst.domain.WANoteVO;

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
//		vo.setW_tag1("MapperTest");
//		vo.setW_tag2("MapperTest");
//		
//		log.info(mapper.createWANote(vo));
//	}
	
//	@Test
//	public void testList1() {
//		log.info(mapper.listWANote("ggy"));
//	}
	
//	@Test
//	public void testRead() {
//		log.info(mapper.readWANote("w2"));
//	}
	
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
	

}
