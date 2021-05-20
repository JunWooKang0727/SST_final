package org.sst.domain;

import lombok.Data;

@Data
public class QuestionSearchVO {
	private String[] area;
	private String searchKey;
	
	public QuestionSearchVO() {}
	
	public QuestionSearchVO(String[] area, String searchKey) {
		super();
		this.area = area;
		this.searchKey = searchKey;
	}
	

	public String[] getArea() {
		return area;
	}

	public void setArea(String[] area) {
		this.area = area;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
}
