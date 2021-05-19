package org.sst.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMemberVO {
	private String gm_num;
	private String g_num;
	private int gm_index;
	private int p_grant;
	private String m_id;
}
