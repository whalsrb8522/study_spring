package com.myweb.www.handler;

import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev,next;
	private int totalCount;
	private PagingVO pvo;

	public PagingHandler(PagingVO pvo, int totalCount) {
		this.pvo = pvo;
		this.totalCount = totalCount;

		this.endPage = (int) (Math.ceil(pvo.getPageNo() / (pvo.getQty() * 1.0)) * pvo.getQty());

		this.startPage = endPage - 9;

		int realEndPage = (int) (Math.ceil((totalCount * 1.0) / pvo.getQty()));

		if (realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}

		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
}