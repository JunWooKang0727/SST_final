package org.sst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.domain.WanoteAttachVO;
import org.sst.mapper.WanoteAttachMapper;

import lombok.Setter;

@Service
public class WanoteAttachServiceImpl implements WanoteAttachService {
	@Setter(onMethod_ = @Autowired)
	WanoteAttachMapper mapper;
	@Override
	public boolean insert(WanoteAttachVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo)==1;
	}

	@Override
	public List<WanoteAttachVO> list(String w_num) {
		// TODO Auto-generated method stub
		return mapper.list(w_num);
	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		return mapper.delete(uuid)==1;
	}

	@Override
	public int deleteAll(String uuid) {
		// TODO Auto-generated method stub
		return mapper.deleteAll(uuid);
	}

}
