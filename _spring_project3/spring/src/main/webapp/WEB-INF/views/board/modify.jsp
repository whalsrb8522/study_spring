<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/modify" method="post">
		<input type="hidden" name="bno" value="${bvo.bno }">
		title : <input type="text" name="title" value="${bvo.title }"> <br>
		writer : <input type="text" name="writer" value="${bvo.writer }"> <br>
		content : <input type="text" name="content" value="${bvo.content }"> <br>
		<button type="submit">수정</button>
	</form>
	<a href="/board/remove?bno=${bvo.bno }">글삭제</a>
</body>
</html>