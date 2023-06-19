<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	}
</style>
</head>
<body>
	<div id="container">
		<form onsubmit="return checkModify()" action="/user/modify" 
		name="form" method="post" id="innerContainer" class="border-gray">
			<p>아이디</p>
			<input type="text" name="id" placeholder="아이디 (* 필수)" value="${uvo.id }" readonly="readonly" class="input-text bg-gray">
			<p>이름</p>
			<input type="text" name="name" placeholder="이름 (* 필수)" value="${uvo.name }" class="input-text">
			<p>이메일</p>
			<input type="email" name="email" placeholder="이메일" value="${uvo.email }" class="input-text">
			<p>주소</p>
			<input type="text" name="home" placeholder="주소" value="${uvo.home }" class="input-text">
			<p>나이</p>
			<input type="text" name="age" placeholder="나이" value="${uvo.age	 }" class="input-text">
			<button type="submit" class="button-green">회원수정</button>
			<a href="/">홈으로</a>
		</form>
	</div>
	
	<script type="text/javascript">
		function checkModify() {
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