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
public class UserVO {

	private String id;
	private String pw;
	private String name;
	private String email;
	private String home;
	private Integer age;
	private String reg_date;
	private boolean isDel;
	
}
