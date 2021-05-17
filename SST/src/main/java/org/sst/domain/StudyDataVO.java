package org.sst.domain;

import lombok.Data;

@Data
public class StudyDataVO {
	
	private String fileName;
	private String uploadPath;
	private String uuid; //안할수도 있음
	private boolean fileType;
	
	private String g_num;
	private String uploader;
	private String regdate;
	
	
}
