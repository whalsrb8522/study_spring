package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.UserVO;
import com.myweb.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO udao;

	@Inject
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public int registerUser(UserVO uvo) {
		log.info("registerUser()");

		// 아이디가 공백인 경우
		// 비밀번호가 공백인 경우
		if (uvo.getId() == null || uvo.getId().length() == 0 || uvo.getPw() == null || uvo.getPw().length() == 0) {
			return 0;
		}

		// 아이디가 중복되면 회원가입 실패
		// 아이디를 DB로 넘기고, 일치하는 유저가 없는 경우에만 가입
		UserVO tempUser = udao.getUser(uvo.getId());

		// 아이디가 중복일 경우
		if (tempUser != null) {
			return 0;
		}

		// 회원가입 진행
		String pw = uvo.getPw();
		// encode(암호화) / matches(원래 비번, 암호화된 비번)
		String encodePw = passwordEncoder.encode(pw);	// 기존 비밀번호 암호화
		// uvo의 패스워드를 암호화된 패스워드로 수정
		uvo.setPw(encodePw);
		// 회원가입
		int isOk = udao.insertUser(uvo);
//		log.info("signUp() > isOk : " + isOk);

		return isOk;
	}

	@Override
	public UserVO getUser(UserVO uvo) {
		log.info("getUser()");

		// 아이디가 공백인 경우
		// 비밀번호가 공백인 경우
		if (uvo.getId() == null || uvo.getId().length() == 0 || uvo.getPw() == null || uvo.getPw().length() == 0) {
			return null;
		}

		// 로그인 수행 (id만으로 비교)
		UserVO resultUvo = udao.getUser(uvo.getId());

		// 입력한 비밀번호와, 가져온 비밀번호가 같은지 비교
		if(passwordEncoder.matches(uvo.getPw(), resultUvo.getPw())) {
			return resultUvo;
		} else {
			return null;
		}
	}

}
