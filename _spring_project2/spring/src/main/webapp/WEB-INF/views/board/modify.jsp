<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		resize: none;
	}
</style>
</head>
<body>
	<div id="container">
		<form onsubmit="return modifyCheck()" action="/board/modify" method="post" name="form" id="innerContainer" class="border-gray input-title">
			<input type="hidden" name="bno" value="${bvo.bno }">
			<p>제목</p>
			<input type="text" name="title" value="${bvo.title }" class="input-text">
			<p>작성자</p>
			<input type="text" name="writer" value="${bvo.writer}" readonly="readonly" class="input-text bg-gray">
			<p>내용</p>
			<textarea name="content" class="textarea-text border-gray">${bvo.content}</textarea>
			<button type="submit" class="button-green">글수정</button>
			<a href="/board/remove?bno=${bvo.bno }">글삭제</a>
			<a href="javascript:location.href=document.referrer">뒤로가기</a>
		</form>
	</div>
	
	<script type="text/javascript">
		function modifyCheck() {
			if (form.title.value == "") {
				alert("제목을 입력해주세요.");
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>