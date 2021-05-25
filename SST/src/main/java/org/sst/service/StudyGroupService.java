package org.sst.service;

import java.util.List;

import org.sst.domain.Criteria;
import org.sst.domain.Criteria2;
import org.sst.domain.GroupMemberVO;
import org.sst.domain.StudyGroupVO;

public interface StudyGroupService {
	public void groupCreate(StudyGroupVO group, String id);
	public StudyGroupVO groupDetailGet(String gid);
	public boolean groupModify(StudyGroupVO group);
	public boolean groupRemove(String gid);
	public List<StudyGroupVO> myGroupGet(String id, String p_grant);
	public List<StudyGroupVO> attendGroupGet(String id);
	// public List<StudyGroupVO> totalGroupGet();
	public List<StudyGroupVO> totalGroupGet(Criteria2 cri);
	public int getTotal(Criteria2 cri);
	public void joinGroup(GroupMemberVO gm);
	public List<GroupMemberVO> memberListGet(String g_num, String iswait);
	public boolean groupmemAccept(String g_num, String m_id);
	public boolean groupmemDeny(String g_num, String m_id);
}
