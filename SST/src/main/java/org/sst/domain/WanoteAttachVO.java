package org.sst.domain;

import lombok.Data;

@Data
public class WanoteAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	private boolean image;
	private String w_num;
}
