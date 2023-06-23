package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService bsvc;
	
	@GetMapping("register")
	public void getRegister() {
		log.info("getRegister()");
	}
	
	@PostMapping("register")
	public String postRegister(Model m, BoardVO bvo) {
		log.info(">>> postRegister() > bvo : ", bvo);
		bsvc.boardRegister(bvo);
		return "redirect:/";
	}
	
	@GetMapping("list")
	public String getList(Model m) {
		log.info(">>> getList()");
		
		List<BoardVO> listBvo = bsvc.getBoardList();
		log.info(">>> getList() > listBvo : " + listBvo.toString());
		
		m.addAttribute("listBvo", listBvo);
		
		return "/board/list";
	}
	
	@GetMapping("detail")
	public String getDetail(Model m, @RequestParam("bno")int bno, 
	HttpServletRequest req, HttpServletResponse resp) {
		log.info(">>> getDetail() > bno : " + bno);
		
		Cookie oldCookie = null;
	    Cookie[] cookies = req.getCookies();

		
		if (cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("postView")) {
					oldCookie = cookie;
				}
			}
		}
		
		if (oldCookie != null) {
	        if (!oldCookie.getValue().contains("[" + Integer.toString(bno) + "]")) {
	            bsvc.addReadCount(bno);
	            oldCookie.setValue(oldCookie.getValue() + "_[" + Integer.toString(bno) + "]");
	            oldCookie.setPath("/");
	            oldCookie.setMaxAge(60 * 60 * 24);
	            resp.addCookie(oldCookie);
	         } 
		} else {
            bsvc.addReadCount(bno);
	        Cookie newCookie = new Cookie("postView","[" + Integer.toString(bno) + "]");
	        newCookie.setPath("/");
	        newCookie.setMaxAge(60 * 60 * 24);
	        resp.addCookie(newCookie);
	    }
		
		BoardVO bvo = bsvc.getDetail(bno);
		m.addAttribute("bvo", bvo);
		
		return "/board/detail";
	}
	
	@GetMapping("modify")
	public void getModify(Model m, @RequestParam("bno")int bno) {
		log.info(">>> getModify() > bno : " + bno);
		BoardVO bvo = bsvc.getDetail(bno);
		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("modify")
	public String postModify(BoardVO bvo) {
		log.info(">>> postModify() > bvo : " + bvo.toString());
		bsvc.modifyBoard(bvo);
		return "redirect:/board/list";
	}
	
	@GetMapping("remove")
	public String getRemove(@RequestParam("bno")int bno) {
		log.info(">>> getRemove() > bno : " + bno);
		bsvc.removeBoard(bno);		
		return "redirect:/board/list";
	}
	
}
