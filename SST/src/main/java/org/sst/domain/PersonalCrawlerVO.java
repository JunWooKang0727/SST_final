package org.sst.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonalCrawlerVO {
	private String Subject;
	private String[] MonthList;
	private String StartYear;
	private String EndYear;
	private final String Path  = "C:/upload/new/";
	private final String exUrl = "https://wdown.ebsi.co.kr/W61001/01exam";
	
}
