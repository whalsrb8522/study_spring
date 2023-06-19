package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	
	@Override
	public List<BoardVO> getBoardList(PagingVO pvo) {
		log.info("getBoardList() > pvo : " + pvo.toString());
		return bdao.selectBoardList(pvo);
	}

	@Override
	public int getTotalCount(PagingVO pvo) {
		return bdao.selectTotalCount(pvo);
	}

	@Override
	public int registerBoard(BoardVO bvo) {
		return bdao.insertBoard(bvo);
	}

	@Override
	public BoardVO getBoardDetail(int bno) {
		return bdao.selectBoardDetail(bno);
	}

	@Override
	public int modifyBoard(BoardVO bvo) {
		return bdao.updateBoard(bvo);
	}

	@Override
	public int removeBoard(int bno) {
		return bdao.deleteBoard(bno);
	}

}
	