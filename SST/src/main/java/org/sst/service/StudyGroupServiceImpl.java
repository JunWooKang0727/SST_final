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

	@Override
	public List<StudyGroupVO> totalGroupGet(Criteria2 cri) {
		log.info("[get list with criteria : ");
		return mapper.totalgroupList(cri);
	}

	@Override
	public int getTotal(Criteria2 cri) {
		log.info("[get total count]");	
		return mapper.getTotalCount(cri);
	}

	@Override
	public void joinGroup(GroupMemberVO gm) {
		log.info("[join group]");
		mapper.insertGroupMember(gm);
	}

	@Override
	public List<GroupMemberVO> memberListGet(String g_num, String iswait) {
		log.info("[waiting list]");
		return mapper.groupMemberRead(g_num, iswait);
	}

	@Override
	public boolean groupmemAccept(String g_num, String m_id) {
		log.info("[accept]");
		return mapper.acceptGroupMember(g_num, m_id) == 1;
	}

	@Override
	public boolean groupmemDeny(String g_num, String m_id) {
		log.info("[deny]");
		return mapper.denyGroupMember(g_num, m_id) == 1;
	}

	@Override
	public List<StudyGroupVO> myAttendListGet(String id) {
		log.info("[attend]");
		return mapper.groupAttendRead(id);
	}

	@Override
	public List<StudyGroupVO> myWaitListGet(String id) {
		log.info("[wait]");
		return mapper.groupStatusRead(id);
	}

	@Override
	public int getMemTotal(String g_num) {
		log.info("[count mem]");
		return mapper.memberCount(g_num);
	}

	@Override
	public boolean groupmemAuthUpdate(String p_grant, String g_num, String m_id) {
		return mapper.updateGroupAuth(p_grant, g_num, m_id) == 1;
	}

	@Override
	public boolean groupmemDel(String g_num, String m_id) {
		return mapper.delGroupMember(g_num, m_id) == 1;
	}

}
