package org.sst.mapper;

import java.util.HashMap;
import java.util.List;

import org.sst.domain.LicenseScoreVO;
import org.sst.domain.LicenseTestVO;
import org.sst.domain.ReportCardVO;
import org.sst.domain.SchoolScoreVO;
import org.sst.domain.SchoolTestVO;

public interface ReportCardMapper {	
	List<ReportCardVO> listReportCard(String m_id);
	int createReportCard(ReportCardVO vo);
	int updateReportCard(ReportCardVO vo);
	int deleteReportCard(String rc_num);
	ReportCardVO readReportCard(String rc_num);
	
	int createSchoolTest(SchoolTestVO st);
	List<SchoolTestVO> listSchoolTest(String rc_num);
	int deleteSchoolTest(String st_num);
	SchoolTestVO readSchoolTest(String st_num);
	int updateSchoolTest(SchoolTestVO st);
	
	int createSchoolScore(SchoolScoreVO ss);
	List<SchoolScoreVO> listSchoolScore(String st_num);
	int deleteSchoolTestScore(String st_num);
	int deleteSchoolScore(String ss_num);
	int updateSchoolScore(SchoolScoreVO ss);
	
	
	int createLicenseTest(LicenseTestVO lt);
	List<LicenseTestVO> listLicenseTest(String lt_num);
	LicenseTestVO readLicenseTest(String lt_num);
	int updateLicenseTest(LicenseTestVO lt);
	int deleteLicenseTest(String lt_num);
	
	
	int createLicenseScore(LicenseScoreVO ls);
	List<LicenseScoreVO> listLicenseScore(String lt_num);
	int deleteLicenseTestScore(String lt_num);
	int deleteLicenseScore(String ls_num);
	int updateLicenseScore(LicenseScoreVO ls);
	
	
	List<HashMap> averageSchoolTest(String rc_num);
	List<HashMap> allSubjectScoreSchoolTest(HashMap map);
	List<String> schoolTestSubjects(String rc_num);
	
	List<HashMap> allSubjectScoreLicenseTest(HashMap map);
	List<String> licenseTestSubjects(String rc_num);
}
