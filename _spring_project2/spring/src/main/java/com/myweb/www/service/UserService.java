package com.myweb.www.service;

import com.myweb.www.domain.UserVO;

public interface UserService {

	int addUser(UserVO uvo);

	UserVO getUser(UserVO uvo);

	int modifyUser(UserVO uvo);

}
