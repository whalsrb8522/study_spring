package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardVO {

	private int bno;
	private String title;
	private String content;
	private String writer;
	private String reg_date;
	private int readcount;
	private String isDel;
	
}
