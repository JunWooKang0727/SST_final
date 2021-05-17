package org.sst.service;

import java.util.HashMap;

public interface ScoreAnalysisService {
	public HashMap allSubjectScoreSchoolTest(String rc_num);
	public HashMap averageSchoolTest(String rc_num);
	
	public HashMap allSubjectScoreLicenseTest(String rc_num);
}
