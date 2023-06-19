<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<body>
	<div id="container">
		<c:if test="${ses.id eq null }">
			<form onsubmit="return checkSignIn()" action="/user/signin" 
			name="form" method="post" id="innerContainer" class="border-gray">
				<input type="text" name="id" placeholder="아이디" class="input-text">
				<input type="password" name="pw" placeholder="비밀번호" class="input-text">
				<button type="submit" class="button-green">로그인</button>
				<a href="/user/signup">회원가입</a>
			</form>
		</c:if>
		
		<c:if test="${ses.id ne null }">
			<div id="innerContainer" class="border-gray">
				<p>${ses.name } 님 안녕하세요.</p>
				<button type="button" onclick="location.href='/board/list'" class="button-green">게시판</button>
				<a href="/user/modify">회원수정</a>
				<a href="/user/signout">로그아웃</a>
			</div>
		</c:if>
	</div>
	
	<script type="text/javascript">
		const msg = '<c:out value="${msg }"></c:out>';
		
		if (msg != null && msg != "") {
			alert(msg);
		}
	
		function checkSignIn() {
			if (form.id.value == "") {
				alert("아이디를 입력해주세요.");
				return false;
			} else if (form.pw.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			} 
			
			return true;
		}
	</script>
</body>
</html>


