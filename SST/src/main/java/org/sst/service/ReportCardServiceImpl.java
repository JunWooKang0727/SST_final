package org.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.ReportCardVO;
import org.sst.domain.SchoolScoreVO;
import org.sst.domain.SchoolTestVO;
import org.sst.mapper.ReportCardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReportCardServiceImpl implements ReportCardService {
	@Setter(onMethod_ = @Autowired)
	ReportCardMapper mapper;

	@Override
	public List<ReportCardVO> listReportCard(String m_id) {
		return mapper.listReportCard(m_id);
	}
	@Override
	public boolean createReportCard(ReportCardVO vo) {
		return mapper.createReportCard(vo)==1;
	}
	@Override
	public boolean updateReportCard(ReportCardVO vo) {
		return mapper.updateReportCard(vo)==1;
	}
	@Override
	public boolean deleteReportCard(String rc_num) {
		return mapper.deleteReportCard(rc_num)==1;
	}
	@Override
	public ReportCardVO readReportCard(String rc_num) {
		return mapper.readReportCard(rc_num);
	}
	
	//SchoolTest
	@Override
	public String createSchoolTest(SchoolTestVO st) {
		mapper.createSchoolTest(st);
		return st.getSt_num();
	}
	@Override
	public List<SchoolTestVO> listSchoolTest(String rc_num) {
		return mapper.listSchoolTest(rc_num);
	}
	@Override
	public boolean deleteSchoolTest(String st_num) {
		return mapper.deleteSchoolTest(st_num) ==1;
	}
	@Override
	public SchoolTestVO readSchoolTest(String st_num) {
		return mapper.readSchoolTest(st_num);
	}
	@Override
	public boolean updateSchoolTest(SchoolTestVO st) {
		return mapper.updateSchoolTest(st) ==1;
	}
	
	
	//SchoolScore
	@Override
	public boolean createSchoolScore(SchoolScoreVO ss) {
		int result = mapper.createSchoolScore(ss);
		log.info(ss.getSs_num());
		return result==1;
	}
	@Override
	public List<SchoolScoreVO> listSchoolScore(String st_num) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean deleteSchoolTestScore(String st_num) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteSchoolScore(String ss_num) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateSchoolScore(SchoolScoreVO ss) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
	

}
