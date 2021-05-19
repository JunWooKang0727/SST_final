package org.sst.service;

import java.util.List;

import org.sst.domain.StudyGroupVO;

public interface StudyGroupService {
	public void groupCreate(StudyGroupVO group, String id);
	public StudyGroupVO groupDetailGet(String gid);
	public boolean groupModify(StudyGroupVO group);
	public boolean groupRemove(String gid);
	public List<StudyGroupVO> myGroupGet(String id);
}
