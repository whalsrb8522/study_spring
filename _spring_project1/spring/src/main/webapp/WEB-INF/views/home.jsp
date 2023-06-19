<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false"%> --%>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<jsp:include page="./layout/header.jsp"></jsp:include>
	
	<h1>
		Hello world!
	</h1>
	
	<p>My Spring Project</p>
	
	<c:if test="${ses.id eq null }">
		<a href="/user/login"><button type="button">로그인</button></a> <br>
		<a href="/user/signup"><button type="button">회원가입</button></a> <br>
	</c:if>
	
	<c:if test="${ses.id ne null }">
		<h3>${ses.id }(${ses.name })님 환영합니다.</h3>
		<a href="/board/list"><button type="button">게시판</button></a> <br>
		<a href="/user/logout"><button type="button">로그아웃</button></a> <br>
	</c:if>
	
	<jsp:include page="./layout/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		var msg = "${msg}";
	</script>
	<script type="text/javascript" src="/resources/js/alert.js"></script>
</body>
</html>
