package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.UserVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO bdao;
	@Inject
	private FileDAO fdao;

	@Override
	public List<BoardVO> getBoardList(PagingVO pvo) {
		log.info("getBoardList() > pvo : " + pvo.toString());
		return bdao.selectBoardList(pvo);
	}

	@Override
	public BoardVO getBoard(int bno) {
		return bdao.selectBoard(bno);
	}

	@Override
	public void updateReadCount(int bno) {
		bdao.updateReadcount(bno);
	}

	@Override
	public int removeBoard(int bno, UserVO uvo) {
		log.info("deleteBoard()");

		BoardVO tmpBvo = bdao.selectBoard(bno);
		if (uvo == null || uvo.getId().equals(tmpBvo.getWriter())) {
			return 0;
		}

		return bdao.deleteBoard(bno);
	}

	@Override
	public int registerBoard(BoardDTO bdto) {
		log.info("registerBoard(BoardDTO)");
		
		int isOk = bdao.insertBoard(bdto.getBvo());
		if (bdto.getListFvo() == null) {	// 파일이 없을 경우 성공한것으로 처리
			isOk *= 1;
		} else {
			if (isOk > 0 && bdto.getListFvo().size() > 0) {
				// register 등록시 bno가 결정되어있지 않음
//				int bno = bdto.getBvo().getBno();		// update시 사용 가능
				int bno = bdao.selectBno();		// 방금 저장된 bvo의 bno 리턴받기
				for (FileVO fvo : bdto.getListFvo()) {
					fvo.setBno(bno);
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		
		return isOk;
	}

	@Override
	public int modifyBoard(BoardDTO bdto) {
		log.info("modifyBoard()");
		
		// 기존 보드 내용 업데이트
		int isOk = bdao.updateBoard(bdto.getBvo());
		
		if (bdto.getListFvo() == null) {
			isOk *= 1;
		} else if (isOk > 0 && bdto.getListFvo().size() > 0) {
			int bno = bdto.getBvo().getBno();
			
			for (FileVO fvo : bdto.getListFvo()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}

		return isOk;
	}


	@Override
	public int getTotalCount(PagingVO pvo) {
		return bdao.selectTotalCount(pvo);
	}

	@Override
	public int registerBoard(BoardVO bvo) {
		if (bvo.getTitle() == null || bvo.getTitle().length() == 0) {
			return 0;
		}

		return bdao.insertBoard(bvo);
	}

	@Override
	public BoardDTO getBoardFile(int bno) {
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bdao.selectBoard(bno));
		bdto.setListFvo(fdao.getFileList(bno));
		return bdto;
	}

	@Override
	public int removeFile(String uuid) {
		return fdao.deleteFile(uuid);
	}

}
