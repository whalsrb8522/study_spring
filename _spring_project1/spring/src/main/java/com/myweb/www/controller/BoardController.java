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
	private UserDAO udao;
	@Inject
	private FileHandler fhd;

	@GetMapping("/list")
	public void list(Model m, PagingVO pvo, HttpServletRequest req) {
		log.info("list()");

		List<BoardVO> listBvo = bsvc.getBoardList(pvo);

		int totalCount = bsvc.getTotalCount(pvo);
		log.info("list() > totalCount : " + totalCount);
		PagingHandler ph = new PagingHandler(pvo, totalCount);

		m.addAttribute("listBvo", listBvo);
		m.addAttribute("ph", ph);
	}

	@GetMapping({"/detail", "/modify"})
	public void detail(Model m, @RequestParam("bno")int bno, HttpServletRequest req) {
		log.info("detail()");
		log.info(req.getRequestURI());

		BoardDTO bdto = bsvc.getBoardFile(bno);

		String mapping = req.getRequestURI();
		String path = mapping.substring(mapping.lastIndexOf("/") + 1);

		if (path.equals("detail")) {
			bsvc.updateReadCount(bno);
		}

		m.addAttribute("bdto", bdto);
	}

	@GetMapping("/register")
	public void register(Model m) {
		log.info("register()");
	}

	@PostMapping("/register")
	// required : 필수 여부 (false : 해당 파라미터가 없다라도 예외가 발생하지 않음
	public String registerPost(RedirectAttributes rattr, BoardVO bvo, 
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

	@PostMapping("/modify")
	public String modifyPost(RedirectAttributes rattr, BoardVO bvo, HttpServletRequest req,
			@RequestParam(name = "files", required = false)MultipartFile[] files) {
		log.info("modifyPost()");
		log.info("modifyPost() > bvo : " + bvo.toString());
		log.info("modifyPost() > files : " + files.toString());

		
		List<FileVO> listFvo = new ArrayList<FileVO>();
		if (files[0].getSize() > 0) {
			listFvo = fhd.uploadFiles(files);
		}
		BoardDTO bdto = new BoardDTO(bvo, listFvo);
		
		int isOk = bsvc.modifyBoard(bdto);

		String msg;
		if (isOk > 0) {
			msg = "";
		} else {
			msg = "";
		}

		rattr.addFlashAttribute("msg", msg);

		return "redirect:/board/list";
	}

	@GetMapping("/delete")
	public String delete(RedirectAttributes rattr, @RequestParam("bno")int bno, HttpServletRequest req) {
		log.info("delete()");

		HttpSession ses = req.getSession();

		UserVO sesUvo = (UserVO) ses.getAttribute("ses");
		UserVO uvo = udao.getUser(sesUvo.getId());

		bsvc.removeBoard(bno, uvo);

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
