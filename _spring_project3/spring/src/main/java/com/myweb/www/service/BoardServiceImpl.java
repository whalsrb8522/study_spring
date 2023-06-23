package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	
	public int boardRegister(BoardVO bvo) {
		log.info(">>> boardRegister() > bvo : " + bvo);
		return bdao.insertBoard(bvo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		log.info("getBoardList()");
		return bdao.selectAllBoard();
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("getDetail()");
		return bdao.selectDetailBoard(bno);
	}

	@Override
	public void modifyBoard(BoardVO bvo) {
		bdao.updateBoard(bvo);
	}

	@Override
	public void removeBoard(int bno) {
		bdao.deleteBoard(bno);
	}

	@Override
	public void addReadCount(int bno) {
		bdao.updateReadCount(bno);
	}

}
