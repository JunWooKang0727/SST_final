package org.sst.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria2 {
	private int pageNum;
	private int amount;
	
	private String type;
	private String category;
	private String keyword;
	
	public Criteria2() {
		this(1,12);
	}

	public Criteria2(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {

		return type == null ? new String[] {} : type.split("");
	}
	
	public String[] getCategoryArr(){
		return category == null ? new String[] {} : type.split("");
	}

	public String getListLink() {

		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("category", this.getCategory())
				.queryParam("keyword", this.getKeyword());

		return builder.toUriString();

	}
}
