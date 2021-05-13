package org.sst.service;

import org.sst.domain.MemberVO;

public interface MemberService {
	
	public void memberSignup(MemberVO member); 
	
	public MemberVO memberGet(String id);
	
	public boolean memberModify(MemberVO member);
	
	public boolean memberRemove(MemberVO member);
	
	public int memberIdCount(String id);
}
