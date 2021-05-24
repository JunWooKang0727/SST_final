package org.sst.mapper;

import java.util.List;

import org.sst.domain.Criteria;
import org.sst.domain.Criteria2;
import org.sst.domain.GroupMemberVO;
import org.sst.domain.StudyGroupVO;

public interface StudyGroupMapper {
	// 그룹생성
	public int groupInsert(StudyGroupVO group);
	
	public void insertGroupMember(GroupMemberVO gmem);
	
	public StudyGroupVO groupDetailRead(String g_num);
	
	public int groupUpdate(StudyGroupVO group);

	public int groupDelete(String gnum);
	
	public List<StudyGroupVO> groupMakeRead(String id);
	
	//public List<StudyGroupVO> totalgroupList();
	
	public List<StudyGroupVO> totalgroupList(Criteria2 cri);
	
	public int getTotalCount(Criteria2 cri);
	
//	public List<StudyGroupVO> searchGroup(Criteria2 cri);
}
