<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>SignUp Page</h1>
	
	<form action="/user/signup" method="post">
		ID : <input type="text" name="id" placeholder="아이디"> <br>
		PW : <input type="password" name="pw" placeholder="비밀번호"> <br>
		NAME : <input type="text" name="name" placeholder="이름"> <br>
		EMAIL : <input type="email" name="email" placeholder="이메일"> <br>
		HOME : <input type="text" name="home" placeholder="주소"> <br>
		AGE : <input type="text" name="age" placeholder="나이"> <br>
		<button type="submit">회원가입</button>
	</form>
</body>
</html>