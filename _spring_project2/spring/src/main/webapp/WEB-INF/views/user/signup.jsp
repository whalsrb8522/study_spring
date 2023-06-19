<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
</head>
<body>
	<div id="container">
		<form onsubmit="return checkSignUp()" action="/user/signup" 
		name="form" method="post" id="innerContainer" class="border-gray">
			<input type="text" name="id" placeholder="아이디 (* 필수)" class="input-text">
			<input type="password" name="pw" placeholder="비밀번호 (* 필수)" class="input-text" >
			<input type="text" name="name" placeholder="이름 (* 필수)" class="input-text" value="">
			<input type="email" name="email" placeholder="이메일" class="input-text" value="">
			<input type="text" name="home" placeholder="주소" class="input-text" value="">
			<input type="text" name="age" placeholder="나이" class="input-text" value="">
			<button type="submit" id="submitBtn" class="button-green">회원가입</button>
			<a href="/">홈으로</a>
		</form>
	</div>
	
	<script type="text/javascript" src="/resources/js/test.js"></script>
	<script type="text/javascript">
		function checkSignUp() {
			if (form.id.value == "") {
				alert("아이디를 입력해주세요.");
				return false;
			} else if (form.pw.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			} else if (form.name.value =="") {
				alert("이름을 입력해주세요.");
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>