package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Override
	public List<BoardVO> getBoardList(PagingVO pvo) {
		return null;
	}

	@Override
	public int getTotalCount(PagingVO pvo) {
		return 0;
	}

}
	