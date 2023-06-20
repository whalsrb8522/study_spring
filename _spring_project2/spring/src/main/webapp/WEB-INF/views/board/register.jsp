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
		<form onsubmit="return registerCheck()" action="/board/register" method="post" enctype="multipart/form-data" name="form" id="innerContainer" class="border-gray input-title">
			<p>제목</p>
			<input type="text" name="title" placeholder="제목" class="input-text">
			<p>작성자</p>
			<input type="text" name="writer" placeholder="작성자" value="${ses.id }" readonly="readonly" class="input-text bg-gray">
			<p>내용</p>
			<textarea name="content" class="textarea-text"></textarea>
			<p>첨부파일</p>
			<input type="file" id="file" name="files" multiple="multiple" style="display: none">
			<button type="button" id="trigger" class="button-green">파일선택</button> <br>
			<div id="fileZone">
			
			</div>
			<button type="submit" id="regBtn" class="button-green">글쓰기</button>
			<a href="javascript:location.href=document.referrer">뒤로가기</a>
		</form>
	</div>
	
	<script type="text/javascript" src="/resources/js/board_register.js"></script>
	<script type="text/javascript">
		function registerCheck() {
			if (form.title.value == "") {
				alert("제목을 입력해주세요.");
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>