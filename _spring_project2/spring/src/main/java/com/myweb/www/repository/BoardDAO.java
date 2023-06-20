package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	List<BoardVO> selectBoardList(PagingVO pvo);

	int selectTotalCount(PagingVO pvo);

	int insertBoard(BoardVO bvo);

	BoardVO selectBoardDetail(int bno);

	int updateBoard(BoardVO bvo);

	int deleteBoard(int bno);

	int selectBno();

	void updateReadcount(int bno);

}
