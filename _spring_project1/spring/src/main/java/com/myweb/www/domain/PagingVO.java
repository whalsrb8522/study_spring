package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO {

	private int pageNo;		// 현재 화면에 출력된 페이지네이션 번호
	private int qty;		// 한 페이지에 보여주는 게시글 수

	private String type;
	private String keyword;

	public PagingVO() {
		this(1, 10);
	}

	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	public int getPageStart() {		// DB상 limit의 시작값
		// limit의 시작값은 0부터 10개 -> limit 0, 10
		return (this.pageNo - 1) * qty;
	}

	public String[] getTypeToArray() {
		return this.type.split("");
	}

}
