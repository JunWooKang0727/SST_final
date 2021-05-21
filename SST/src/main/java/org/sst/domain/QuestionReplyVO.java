package org.sst.domain;

import java.util.Date;



import lombok.Data;

@Data
public class QuestionReplyVO {
	private String rno;
	private String q_num;
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}