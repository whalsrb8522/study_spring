package com.myweb.www.service;

import com.myweb.www.domain.UserVO;

public interface UserService {

	int registerUser(UserVO uvo);

	UserVO getUser(UserVO uvo);

}
