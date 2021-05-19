package org.sst.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.sst.domain.GroupMemberVO;
import org.sst.domain.StudyGroupVO;
import org.sst.mapper.StudyGroupMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class StudyGroupServiceImpl implements StudyGroupService {

	private StudyGroupMapper mapper; 
	
	
	@Override
	public void groupCreate(StudyGroupVO group, String id) {
		log.info("[group Create]");
		int result = mapper.groupInsert(group);
		log.info("result");
		GroupMemberVO gmem = new GroupMemberVO();
		gmem.setG_num(group.getG_num());
		gmem.setM_id(id);
		gmem.setP_grant(1);
		mapper.insertGroupMember(gmem);
	}

	@Override
	public StudyGroupVO groupDetailGet(String gid) {
		log.info("[group Detail Get]");
		return mapper.groupDetailRead(gid);
	}

	@Override
	public boolean groupModify(StudyGroupVO group) {
		log.info("[group Modify]");
		return mapper.groupUpdate(group) == 1;
	}

	@Override
	public boolean groupRemove(String gid) {
		log.info("[group Remove]");
		return mapper.groupDelete(gid) == 1;
	}

	@Override
	public List<StudyGroupVO> myGroupGet(String id) {
		List<StudyGroupVO> mygrouplist = mapper.groupMakeRead(id);
		log.info("[get my group list]");
		return mygrouplist;
	}

	
	
}
