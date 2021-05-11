package org.sst.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_phone;
	private String m_birth;
	private String m_email;
}
