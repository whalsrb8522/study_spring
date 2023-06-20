<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<style type="text/css">
	.input-text {
		margin-top: 10px;
	}
	
	.textarea-text {
		margin-top: 10px;
	}
	
	#container {
		display: flex;
	}
	
	#commentContainer {
		position: relative;
		width: 500px;
		padding: 35px;
		text-align: center;
	    display: flex;
	    flex-direction: column;
	    margin-left: 10px;
	}

	#commentContainer input {
		height: 40px;
		padding: 0 10px;
	}
	
	#commentContainer button {
		height: 40px;
		padding: 0 10px;
	}
	
	#commentWriteContainer {
		display: flex;
		align-items: flex-end;
	}
	
	#commentWriteContainer input {
		flex: auto;
	}
</style>
</head>
<body>
	<div id="container">
		<div id="innerContainer" class="border-gray input-title">
			<p>제목</p>
			<input type="text" value="${bvo.title }" readonly="readonly" class="input-text border-none">
			<p>작성자</p>
			<input type="text" value="${bvo.writer}" readonly="readonly" class="input-text border-none">
			<p>내용</p>
			<textarea readonly="readonly" class="textarea-text border-gray">${bvo.content}</textarea>
			<button onclick="location.href='/board/modify?bno=${bvo.bno}'" class="button-green">글수정</button>
			<a href="/board/list">목록으로</a> <br>
		</div>
		
		<div id="commentContainer" class="border-gray input-title">
			<p>댓글</p>
			
			<div id="commentWriteContainer">
				<input type="hidden" id="cmtWriter" value="${ses.id }">
				<input type="text" id="cmtText" placeholder="댓글을 작성해주세요." class="input-text">
				<button type="button" id="cmtPostBtn" class="button-green">댓글작성</button>
			</div>
			
			<ul id="cmtListArea">
				<li>
					<div>
						Writer (reg_date)<br>
						Content for Comment 
					</div>
				</li>
			</ul>
		</div>
	</div>
	
	<script type="text/javascript">
		const bnoVal = '<c:out value="${bvo.bno}" />';
	</script>
	<script type="text/javascript" src="/resources/js/board_comment.js"></script>
	<script type="text/javascript">
		getCommentList(bnoVal)	
	</script>
</body>
</html>
