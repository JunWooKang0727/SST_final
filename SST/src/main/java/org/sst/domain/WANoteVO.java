package org.sst.domain;

import java.util.List;

import lombok.Data;

@Data
public class WANoteVO {
	private String w_num;
	private String w_title;
	private String w_question;
	private String w_answer;
	private String w_reason;
	private String w_tag1;
	private String w_tag2;
	private String m_id;
	
	private List<WANoteAttachFile> files;
	
}
