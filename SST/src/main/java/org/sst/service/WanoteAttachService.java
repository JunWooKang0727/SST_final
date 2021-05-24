package org.sst.service;

import java.util.List;

import org.sst.domain.WanoteAttachVO;

public interface WanoteAttachService {
	public boolean insert(WanoteAttachVO vo);
	public List<WanoteAttachVO> list(String w_num);
	public boolean delete(String uuid);
	public int deleteAll(String uuid);
}
