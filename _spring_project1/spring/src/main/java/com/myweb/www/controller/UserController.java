 package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.UserVO;
import com.myweb.www.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user/*")
public class UserController {

	// private static final Logger logger = LoggerFactory.getLogger(UserController.class);	-> @Slf4j 어노테이션 사용시 선언할 필요 없음

	@Inject
	private UserService usvc;

	@GetMapping("/signup")
	public String signUp(Model m) {
		log.info("signUp()");

		return "/user/signup";
	}

	@PostMapping("/signup")
	public String signUpPost(RedirectAttributes rattr, UserVO uvo) {
		log.info("signUpPost()");
//		log.info("signUpPost() > uvo : " + uvo.toString());

		int isOk = usvc.registerUser(uvo);

		if (isOk > 0) {
			rattr.addAttribute("msg", "회원가입을 축하합니다.");
		} else {
			rattr.addAttribute("msg", "아이디/비밀번호를 확인해주세요.");
		}
		return "redirect:/home";
	}

	@GetMapping("/login")
	public String login(Model m) {
		log.info("login()");

		return "/user/login";
	}

	@PostMapping("login")
	public String loginPost(Model m, UserVO uvo, HttpServletRequest req) {
		log.info("loginPost()");
//		log.info("loginPost() > uvo : " + uvo.toString());

		UserVO resultUvo = usvc.getUser(uvo);

		if (resultUvo != null) {
			HttpSession ses = req.getSession();
			ses.setAttribute("ses", resultUvo);
			ses.setMaxInactiveInterval(60 * 100);

			return "/home";
		} else {
			m.addAttribute("msg", "아이디/비밀번호를 확인해주세요.");
			return "/home";
		}
	}

	@GetMapping("/logout")
	public String logout(Model m, HttpServletRequest req) {
		HttpSession ses = req.getSession();
		ses.removeAttribute("ses");
		ses.invalidate();

		m.addAttribute("msg", "로그아웃 되었습니다.");
		return "/home";
	}
}
