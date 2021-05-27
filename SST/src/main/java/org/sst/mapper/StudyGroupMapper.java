package org.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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

	public List<StudyGroupVO> groupRead(@Param("id") String id, @Param("p_grant") String p_grant);
	
	public List<StudyGroupVO> groupStatusRead(String id);
	
	public List<StudyGroupVO> groupAttendRead(String id);
	
	public List<StudyGroupVO> totalgroupList(Criteria2 cri);
	
	public int memberCount(String g_num);
	
	public int getTotalCount(Criteria2 cri);
	
	public List<GroupMemberVO> groupMemberRead(@Param("g_num") String g_num, 
			@Param("iswait") String iswait);
	
	public int acceptGroupMember(@Param("g_num") String g_num, @Param("m_id") String m_id);
	public int denyGroupMember(@Param("g_num") String g_num, @Param("m_id") String m_id);

	public int updateGroupAuth(@Param("p_grant") String p_grant, @Param("g_num") String g_num,
			@Param("m_id") String m_id);
	
	public int delGroupMember(@Param("g_num") String g_num, @Param("m_id") String m_id);

}
