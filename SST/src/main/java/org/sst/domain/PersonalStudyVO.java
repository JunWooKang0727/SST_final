package org.sst.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class PersonalStudyVO implements Serializable{
	private String ps_num;
	private String ps_category;
	private String ps_time;
	private String ps_place;
	private Date ps_starttime;
	
}
