package org.sst.domain;

import java.util.List;

import lombok.Data;

@Data
public class SchoolTestVO {
	private String st_num;
	private String st_date;
	private int st_year;
	private int st_semester;
	private String st_test;
	private String rc_num;
	
	private List<SchoolScoreVO> scorelist;
}
