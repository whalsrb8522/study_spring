package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.CommentVO;

public interface CommentService {

	int registerComment(CommentVO cvo);

	List<CommentVO> getCommentList(int bno);

	int removeComment(int cno);

	int modifyComment(CommentVO cvo);

}
