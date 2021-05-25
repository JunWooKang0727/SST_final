package org.sst.domain;

public class PdfIndexInImage {
	private int page;
	private double xIndex;
	private double yIndex;
	
	public PdfIndexInImage(){
		
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public double getxIndex() {
		return xIndex;
	}

	public void setxIndex(double xIndex) {
		this.xIndex = xIndex;
	}

	public double getyIndex() {
		return yIndex;
	}

	public void setyIndex(double yIndex) {
		this.yIndex = yIndex;
	}
	
}
