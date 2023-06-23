<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	ul {
		cursor: pointer;
	}
	
	ul:hover {
		background-color: #eee;
	} 
</style>
</head>
<body>
	<ol>
		<c:forEach items="${listBvo }" var="bvo" >
			<li>
				<ul onclick="javascript:location.href='/board/detail?bno=${bvo.bno}'">
					<li>제목 : ${bvo.title }</li>
					<li>조회수 : ${bvo.readCount }</li>
					<li>작성자 : ${bvo.writer }</li>
					<li>내용 : ${bvo.content }</li> 
				</ul> 
			</li>
		</c:forEach>
	</ol>
	
	<a href="/">홈으로</a>
</body>
</html>