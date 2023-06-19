package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.UserVO;

public interface BoardService {

	List<BoardVO> getBoardList(PagingVO pvo);

	BoardVO getBoard(int bno);

	int modifyBoard(BoardDTO bdto);

	int removeBoard(int bno, UserVO uvo);

	void updateReadCount(int bno);

	int getTotalCount(PagingVO pvo);

	int registerBoard(BoardVO bvo);

	int registerBoard(BoardDTO bdto);

	BoardDTO getBoardFile(int bno);

	int removeFile(String uuid);

}
