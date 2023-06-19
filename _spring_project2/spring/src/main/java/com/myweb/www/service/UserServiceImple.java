package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.UserVO;
import com.myweb.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImple implements UserService {
	
	@Inject
	private UserDAO udao;

	@Override
	public int addUser(UserVO uvo) {
		if (udao.selectCountUser(uvo) > 0) {
			return -1;
		} else {
			return udao.insertUser(uvo);
		}
	}

	@Override
	public UserVO getUser(UserVO uvo) {
		return udao.selectUser(uvo);
	}

	@Override
	public int modifyUser(UserVO uvo) {
		return udao.updateUser(uvo);
	}

	@Override
	public int removeUser(String id) {
		return udao.deleteUser(id);
	}

}
