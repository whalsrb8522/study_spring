package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.UserVO;
import com.myweb.www.repository.UserDAO;
import com.myweb.www.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user/*")
public class UserController {
	
	@Inject
	private UserService usvc;
	@Inject
	private UserDAO udao;
	
	@PostMapping("signin")
	public String postSignIn(RedirectAttributes rattr, UserVO uvo, HttpSession ses)  {
		log.info("postSignIn()");
		
		UserVO resultUvo = usvc.getUser(uvo);
		
		if (resultUvo == null) {
			rattr.addFlashAttribute("msg", "입력하신 정보가 잘못되었습니다.");
		} else {
			ses.setAttribute("ses", resultUvo);
			ses.setMaxInactiveInterval(60 * 10);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("signout")
	public String getSignOut(HttpSession ses) {
		log.info("getSignOut()");
		
		ses.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("signup")
	public void getSignUp() {
		log.info("getSignUp()");
	}
	
	@PostMapping("signup")
	public String postSignUp(RedirectAttributes rattr, UserVO uvo, HttpSession ses) {
		log.info("postSignUp() > uvo : " + uvo.toString()) ;
		
		int isOk = usvc.addUser(uvo);
		
		if (isOk > 0) {
			postSignIn(rattr, uvo, ses);
		}
		
		return "redirect:/";
	}
	
	@PostMapping(value = "signup_check", consumes = "application/json")
	public ResponseEntity<String> postSignUpCheck(@RequestBody UserVO uvo) {
		log.info("postSignUpCheck()");
		
		int isOk = udao.selectCountUser(uvo);
		log.info("postSignUpCheck() > isOk : " + isOk);	
		
		return isOk > 0 ?
				new ResponseEntity<String>("0", HttpStatus.OK) :
				new ResponseEntity<String>("1", HttpStatus.OK);
	}

	@GetMapping("modify")
	public void getModify(Model m, HttpSession ses) {
		log.info("getModify() > uvo : " + (UserVO) ses.getAttribute("ses"));

		UserVO resultUvo = usvc.getUser((UserVO) ses.getAttribute("ses"));
		
		m.addAttribute("uvo", resultUvo);
	}
	
	@PostMapping("modify")
	public String postModify(RedirectAttributes rattr, UserVO uvo, HttpSession ses) {
		log.info("postModify() > uvo : " + uvo);
		
		int isOk = usvc.modifyUser(uvo);
		log.info("postModify() > isOk : " + isOk);
		
		if (isOk > 0) {
			UserVO resultUvo = usvc.getUser(uvo);

			ses.setAttribute("ses", resultUvo);
			ses.setMaxInactiveInterval(60 * 10);
		}
		
		return "redirect:/";
	}
	
	@PostMapping(value = "modify_check", consumes = "application/json")
	public ResponseEntity<String> postModifyCheck(@RequestBody UserVO uvo) {
		log.info("postModifyCheck()");
		log.info("postModifyCheck() > uvo : " + uvo.toString());

		String pw = udao.selectUserPw(uvo);
		log.info("postModifyCheck() > pw : " + pw);
		
		return pw.equals(uvo.getPw()) ?
				new ResponseEntity<String>("1", HttpStatus.OK) : 
				new ResponseEntity<String>("0", HttpStatus.OK);
	}
	
	@GetMapping("remove")
	public String getRemove(RedirectAttributes rattr, HttpSession ses, @RequestParam("id")String id) {
		log.info("getRemove()");
		
		int isOk = usvc.removeUser(id);
		
		if (isOk > 0) {
			rattr.addFlashAttribute("msg", "탈퇴되었습니다.");
			ses.invalidate();
		}
		
		return "redirect:/";
	}
}
