package org.sst.domain;

import lombok.Data;

@Data
public class LicenseScoreVO {
	private String ls_num;
	private String ls_name;
	private int ls_score;
	private int ls_goal;
	private String lt_num;
}
