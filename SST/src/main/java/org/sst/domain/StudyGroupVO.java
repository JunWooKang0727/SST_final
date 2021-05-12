package org.sst.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyGroupVO {
	private String g_num;
	private String g_name;
	private String g_content;
	private String g_secreat;
	private String g_passwd;
	private String g_category;
	private String g_memnum;
}
