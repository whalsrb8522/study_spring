package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentDAO cdao;

	@Override
	public int registerComment(CommentVO cvo) {
		return cdao.insertComment(cvo);
	}

	@Override
	public List<CommentVO> getCommentList(int bno) {
		return cdao.getCommentList(bno);
	}

	@Override
	public int removeComment(int cno) {
		return cdao.deleteComment(cno);
	}

	@Override
	public int modifyComment(CommentVO cvo) {
		return cdao.updateComment(cvo);
	}

}
