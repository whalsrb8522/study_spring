package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardService bsvc;

	@GetMapping("list")
	public void getList(Model m, PagingVO pvo) {
		log.info("getList()");

		List<BoardVO> listBvo = bsvc.getBoardList(pvo);

		int totalCount = bsvc.getTotalCount(pvo);
		log.info("list() > totalCount : " + totalCount);
		PagingHandler ph = new PagingHandler(pvo, totalCount);

		m.addAttribute("listBvo", listBvo);
		m.addAttribute("ph", ph);
	}
	
	@GetMapping("register")
	public void getRegister() {
		log.info("getRegister()");
	}
	
	@PostMapping("register")
	public String postRegister(BoardVO bvo) {
		log.info("postRegister()");
		log.info("postRegister() > bvo : " + bvo.toString());
		
		int isOk = bsvc.registerBoard(bvo);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("detail")
	public void getDetail(Model m, @RequestParam("bno")int bno) {
		log.info("getDetail()");
		
		BoardVO resultBvo = bsvc.getBoardDetail(bno);
		
		m.addAttribute("bvo", resultBvo);
	}
	
}
