package org.sst.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonalMakeVO {
	private final String path = "C:/upload/new/";
	private String exFileName;
	private String searchT;
}
