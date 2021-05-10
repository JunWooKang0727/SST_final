package org.sst.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class CalendarTodoVO implements Serializable {
	private String t_num;
	private String t_category;
	private String t_title;
	private String t_contents;
	private Date t_startdate;
	private Date t_enddate;
	private int t_todocheck;
	private String c_num;
	
}
