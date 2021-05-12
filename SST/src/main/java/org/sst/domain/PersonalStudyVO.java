package org.sst.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class PersonalStudyVO implements Serializable{
	private String ps_num;
	private String ps_category;
	private String ps_time;
	private String ps_place;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ps_starttime;
	
}
