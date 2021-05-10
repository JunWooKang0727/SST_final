package org.sst.service;

import java.util.List;

import org.sst.domain.ReportCardVO;
import org.sst.domain.SchoolScoreVO;
import org.sst.domain.SchoolTestVO;

public interface ReportCardService {
	public List<ReportCardVO> listReportCard(String m_id);
	public boolean createReportCard(ReportCardVO vo);
	public boolean updateReportCard(ReportCardVO vo);
	public boolean deleteReportCard(String rc_num);
	public ReportCardVO readReportCard(String rc_num);
	
	public String createSchoolTest(SchoolTestVO st);
	public List<SchoolTestVO> listSchoolTest(String rc_num);
	public boolean deleteSchoolTest(String st_num);
	public SchoolTestVO readSchoolTest(String st_num);
	public boolean updateSchoolTest(SchoolTestVO st);
	
	public boolean createSchoolScore(SchoolScoreVO ss);
	public List<SchoolScoreVO> listSchoolScore(String st_num);
	public boolean deleteSchoolTestScore(String st_num);
	public boolean deleteSchoolScore(String ss_num);
	public boolean updateSchoolScore(SchoolScoreVO ss);
	
	
	
}
