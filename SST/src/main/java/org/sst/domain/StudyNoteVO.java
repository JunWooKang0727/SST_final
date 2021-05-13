package org.sst.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class StudyNoteVO implements Serializable {

	private String sn_num; //노트번호    
	private String sn_title; //노트제목
	private String sn_contents; //노트 내용
	private String sn_date; //노트 작성날짜
	private String sn_writer;
	private String g_num; //어떤 그룹의 노트인지 알기위한 그룹고유번호 
	private String gm_num; //어떤 그룹원이 작성했는지 그룹원고유번호
	


	
}
