package com.myweb.www.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.UserVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.UserDAO;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardService bsvc;
	@Inject
	private FileHandler fhd;

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
	public String postRegister(RedirectAttributes rattr, BoardVO bvo, 
			@RequestParam(name = "files", required = false)MultipartFile[] files) {
		log.info("registerPost()");
		log.info("registerPost() > bvo : " + bvo.toString());
		log.info("registerPost() > files : " + files);
		
		// File 처리 (Handler로 별도 처리)
		List<FileVO> listFvo = new ArrayList<FileVO>();
		if (files[0].getSize() > 0) {		// 데이터가 있을 경우
			listFvo = fhd.uploadFiles(files);
		} else {
			log.info("file null");
		}
		
		// 파일과 보드 처리를 별도로 할것인지 같이 처리할것인지 결정 -> 일반적으로 묶어서 처리 
		BoardDTO bdto = new BoardDTO(bvo, listFvo);
		bsvc.registerBoard(bdto);
		
		return "redirect:/board/list";
	}
		
	@GetMapping({"/detail", "/modify"})
	public void detail(Model m, @RequestParam("bno")int bno, HttpServletRequest req) {
		log.info("detail()");
		log.info(req.getRequestURI());

		BoardDTO bdto = bsvc.getBoardDetail(bno);

		String mapping = req.getRequestURI();
		String path = mapping.substring(mapping.lastIndexOf("/") + 1);

		if (path.equals("detail")) {
			bsvc.updateReadCount(bno);
		}

		m.addAttribute("bdto", bdto);
	}
	
	@PostMapping("modify")
	public String postModify(BoardVO bvo) {
		log.info("postModify()");
		log.info("postModify() > bvo : " + bvo);
		
		int isOk = bsvc.modifyBoard(bvo);
		
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}
	
	@GetMapping("remove")
	public String getRemove(@RequestParam("bno")int bno) {
		log.info("getRemove()");
		
		int isOk = bsvc.removeBoard(bno);
		
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value = "/file/{uuid}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid) {
		log.info("uuid : " + uuid);
		
		return bsvc.removeFile(uuid) > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
				new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
