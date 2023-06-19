package com.myweb.www.repository;

import org.springframework.stereotype.Repository;

import com.myweb.www.domain.UserVO;

@Repository
public interface UserDAO {

	UserVO getUser(String id);

	int insertUser(UserVO uvo);

	UserVO selectUser(UserVO uvo);

}
