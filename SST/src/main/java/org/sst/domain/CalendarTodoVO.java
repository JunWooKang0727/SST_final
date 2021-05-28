package org.sst.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class CalendarTodoVO implements Serializable {
	private String t_num;
	private String t_category;
	private String t_title;
	private String t_contents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date t_startdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date t_enddate;
	private int t_todocheck;
	private String m_id;
}
