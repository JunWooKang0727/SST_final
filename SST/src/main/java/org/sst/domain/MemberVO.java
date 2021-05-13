package org.sst.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_phone;
	private String m_birth;
	private String m_email;
	private List<MemberAuthVO> authList;
}
