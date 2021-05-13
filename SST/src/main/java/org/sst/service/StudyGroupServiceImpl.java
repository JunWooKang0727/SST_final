package org.sst.service;

import org.springframework.stereotype.Service;
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
	public void groupCreate(StudyGroupVO group) {
		log.info("[group Create]");
		mapper.groupInsert(group);
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

	
	
}
