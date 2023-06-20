package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	List<BoardVO> getBoardList(PagingVO pvo);

	int getTotalCount(PagingVO pvo);

	int registerBoard(BoardDTO bdto);

	BoardDTO getBoardDetail(int bno);

	int modifyBoard(BoardVO bvo);

	int removeBoard(int bno);

	void updateReadCount(int bno);

	int removeFile(String uuid);

}
