package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/comment/*")
public class CommentController {

	@Inject
	private CommentService csvc;

	// ResponseEntity
	// value : mapping 값 설정,
	// consumes : 가져오는 값의 형태
	// produes : 보낼는 값의 형태
	@PostMapping(value = "/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		log.info("post()");
		log.info("post() > cvo : " + cvo);

		int isOk = csvc.registerComment(cvo);

		return isOk > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
				new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/list/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno")int bno) {
		log.info("list()");
		log.info("list() > bno : " + bno);
		
		List<CommentVO> list = csvc.getCommentList(bno);
		
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/remove/{cno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("cno")int cno) {
		log.info("remove()");
		
		int isOk = csvc.removeComment(cno);
		
		return isOk > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
				new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value = "/modify", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@RequestBody CommentVO cvo) {
		log.info("modify()");
		log.info("modify() > cvo : " + cvo);
		
		int isOk = csvc.modifyComment(cvo);

		return isOk > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
				new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}