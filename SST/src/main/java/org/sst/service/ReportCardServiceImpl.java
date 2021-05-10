package org.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.ReportCardVO;
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
		// TODO Auto-generated method stub
		return mapper.updateReportCard(vo)==1;
	}

	@Override
	public boolean deleteReportCard(String rc_num) {
		// TODO Auto-generated method stub
		return mapper.deleteReportCard(rc_num)==1;
	}

	@Override
	public ReportCardVO readReportCard(String rc_num) {
		// TODO Auto-generated method stub
		return mapper.readReportCard(rc_num);
	}

}
