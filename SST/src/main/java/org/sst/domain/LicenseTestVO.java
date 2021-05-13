package org.sst.domain;

import java.util.List;

import lombok.Data;

@Data
public class LicenseTestVO {
	private String lt_num;
	private String lt_date;
	private String lt_test;
	private int lt_round;
	private String rc_num;
	
	private List<LicenseScoreVO> scorelist;
}
