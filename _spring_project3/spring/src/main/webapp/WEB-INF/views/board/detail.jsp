<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	title : ${bvo.title } <br>
	ReadCount : ${bvo.readCount } <br>
	writer : ${bvo.writer } <br>
	content : ${bvo.content } <br>
	
	<a href="/board/modify?bno=${bvo.bno }">수정하기</a> <br>
	<a href="/board/list">목록으로</a>
</body>
</html>