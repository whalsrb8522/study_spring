package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardService {

	int boardRegister(BoardVO bvo);

	List<BoardVO> getBoardList();

	BoardVO getDetail(int bno);

	void modifyBoard(BoardVO bvo);

	void removeBoard(int bno);

	void addReadCount(int bno);

}
