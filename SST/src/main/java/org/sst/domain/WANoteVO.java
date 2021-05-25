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
	private String w_subject;
	private String w_date;
	private String m_id;
	
	private List<WAtagVO> taglist;
	private List<WanoteAttachVO> attachList;
	
}
