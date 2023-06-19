package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.UserVO;
import com.myweb.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImple implements UserService {
	
	@Inject
	private UserDAO udao;
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public int addUser(UserVO uvo) {
		if (udao.selectCountUser(uvo) > 0) {
			return -1;
		} else {
			String pw = uvo.getPw();
			
			String encodePw = passwordEncoder.encode(pw);
			uvo.setPw(encodePw);
			
			return udao.insertUser(uvo);
		}
	}

	@Override
	public UserVO getUser(UserVO uvo) {
		UserVO resultUvo = udao.selectUser(uvo);
		
		if (passwordEncoder.matches(uvo.getPw(), resultUvo.getPw())) {
			return resultUvo;
		} else {
			return null;
		}
		
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
