package com.myweb.www.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

@Repository
public interface BoardDAO {

	List<BoardVO> selectBoardList(PagingVO pvo);

	BoardVO selectBoard(int bno);

	int updateBoard(BoardVO bvo);

	int deleteBoard(int bno);

	void updateReadcount(int bno);

	int selectTotalCount(PagingVO pvo);

	int insertBoard(BoardVO bvo);

	int selectBno();

}
