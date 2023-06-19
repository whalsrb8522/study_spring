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
	p {
	    margin-bottom: 0 !important;
	    margin-right: auto;
	    font-size: 13px;
	    border-bottom: 1px solid gray;
	    padding-bottom: 5px;
	}
	
	.input-text {
		border: none;
	}
	
	.input-text:focus {
		outline: none;
	}
	
	.textarea-text {
		margin-top: 10px;
	}
	
	.textarea-text:focus {
		outline: none;
	}
</style>
</head>
<body>
	<div id="container">
		<div id="innerContainer" class="border-gray">
			<p>제목</p>
			<input type="text" value="${bvo.title }" readonly="readonly" class="input-text">
			<p>작성자</p>
			<input type="text" value="${bvo.writer}" readonly="readonly" class="input-text">
			<p>내용</p>
			<textarea readonly="readonly" class="textarea-text border-gray">${bvo.content}</textarea>
			<button onclick="location.href='/board/modify?bno=${bvo.bno}'" class="button-green">글수정</button>
			<a href="javascript:location.href=document.referrer">뒤로가기</a>
		</div>
	</div>
</body>
</html>
