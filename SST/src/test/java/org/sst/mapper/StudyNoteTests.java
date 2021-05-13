package org.sst.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.StudyNoteSearchVO;
import org.sst.domain.StudyNoteVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Log4j
public class StudyNoteTests {
	
	@Setter(onMethod_=@Autowired)
	private StudyNoteMapper mapper;
	
/*	@Test
	public void testRead(){
		
		StudyNoteVO vo = new StudyNoteVO("", "안성민", "q", "", "1", "1");
		
		log.info(vo);
		
		mapper.insertStudyNote(vo);
	}*/
	
//	@Test
//	public void testList(){
//		
//		List<StudyNoteVO> list = mapper.listStudyNote();
//		
//		list.forEach(sno -> log.info(sno));
//		
//	}
	
//	@Test
//	public void testGet(){
//		
//		log.info(mapper.detailStudyNote("sn_1"));
//		
//		log.info(mapper.detailStudyNote("sn_1").toString());
//	}
	
//	@Test
//	public void testUpdate(){
//		
//		StudyNoteVO vo = new StudyNoteVO();
//		
//		vo.setSn_num("sn_21");
//		vo.setSn_contents("수정한 내용");
//		vo.setSn_title("수정한 제목");
//		
//		mapper.updateStudyNote(vo);
//		
//		log.info(mapper.detailStudyNote("sn_21"));
//	}
	
//	@Test
//	public void testDelete(){
//
//		mapper.deleteStudyNote("sn_21");
//	
//	}
	
	
//	@Test
//	public void testSearch(){
//		
//		String arr[] = {"sn_title","sn_contents"};
//		
//		StudyNoteSearchVO search = new StudyNoteSearchVO(arr, "%1%");
//		
//		mapper.listStudyNote(search, new RowBounds(0, 10));
//		
//	}
	
	@Test
	public void testCount(){
		
		String arr[] = {"sn_title","sn_contents"};
		
		StudyNoteSearchVO search = new StudyNoteSearchVO(arr, "%1%");
		
		mapper.countStudyNote(search);
		
	}
}
