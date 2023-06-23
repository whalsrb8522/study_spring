package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> selectAllBoard();

	BoardVO selectDetailBoard(int bno);

	void updateBoard(BoardVO bvo);

	void deleteBoard(int bno);

	void updateReadCount(int bno);

}
