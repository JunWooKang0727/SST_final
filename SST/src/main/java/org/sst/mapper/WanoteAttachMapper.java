package org.sst.mapper;

import java.util.List;

import org.sst.domain.WanoteAttachVO;

public interface WanoteAttachMapper {
	public int insert(WanoteAttachVO vo);
	public List<WanoteAttachVO> list(String w_num);
	public int delete(String uuid);
	public int deleteAll(String uuid);
}
