package org.sst.service;

import java.util.List;

import org.sst.domain.ReportCardVO;

public interface ReportCardService {
	public List<ReportCardVO> listReportCard(String m_id);
	public boolean createReportCard(ReportCardVO vo);
	public boolean updateReportCard(ReportCardVO vo);
	public boolean deleteReportCard(String rc_num);
	public ReportCardVO readReportCard(String rc_num);
	
}
