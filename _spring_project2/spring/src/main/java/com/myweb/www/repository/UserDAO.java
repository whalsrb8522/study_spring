package com.myweb.www.repository;

import org.springframework.stereotype.Repository;

import com.myweb.www.domain.UserVO;

@Repository
public interface UserDAO {

	int insertUser(UserVO uvo);

	UserVO selectUser(UserVO uvo);

	int updateUser(UserVO uvo);

	int selectCountUser(UserVO uvo);

	String selectUserPw(UserVO uvo);

	int deleteUser(String id);

}
