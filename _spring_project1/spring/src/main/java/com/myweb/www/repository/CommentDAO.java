package com.myweb.www.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myweb.www.domain.CommentVO;

@Repository
public interface CommentDAO {

	int insertComment(CommentVO cvo);

	List<CommentVO> getCommentList(int bno);

	int deleteComment(int cno);

	int updateComment(CommentVO cvo);

}
