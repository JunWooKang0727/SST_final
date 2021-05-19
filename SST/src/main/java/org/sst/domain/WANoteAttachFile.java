package org.sst.domain;

import lombok.Data;

@Data
public class WANoteAttachFile {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean image;
	private String filetype;
}
