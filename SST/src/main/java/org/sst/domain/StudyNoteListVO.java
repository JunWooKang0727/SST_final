package org.sst.domain;

import java.util.List;

public class StudyNoteListVO {
	private List<StudyNoteVO> list;
	private int requestPage; 
	private int totalPageCount; //총페이지수 ( 노트페이지수)
	private int startPage; 
	private int endPage;
	
	public StudyNoteListVO() {}

	public StudyNoteListVO(List<StudyNoteVO> list, int requestPage, int totalPageCount, int startPage, int endPage) {
		super();
		this.list = list;
		this.requestPage = requestPage;
		this.totalPageCount = totalPageCount;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public List<StudyNoteVO> getList() {
		return list;
	}

	public void setList(List<StudyNoteVO> list) {
		this.list = list;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
