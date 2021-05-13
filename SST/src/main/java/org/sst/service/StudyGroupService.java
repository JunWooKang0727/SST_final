package org.sst.service;

import org.sst.domain.StudyGroupVO;

public interface StudyGroupService {
	public void groupCreate(StudyGroupVO group);
	public StudyGroupVO groupDetailGet(String gid);
	public boolean groupModify(StudyGroupVO group);
	public boolean groupRemove(String gid);
}
