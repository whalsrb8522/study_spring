package com.myweb.www.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void register() {}
	
	@PostMapping("register")
	public String register(Model m, BoardVO bvo) {
		log.info(">>> bvo {}" + bvo);
		int isOk = bsvc.boardRegister(bvo);
		return "/";
	}
	
	@GetMapping("list")
	
}
