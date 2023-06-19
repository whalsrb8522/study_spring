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
		<form onsubmit="return registerCheck()" action="/board/register" method="post" name="form" id="innerContainer" class="border-gray">
			<input type="text" name="title" placeholder="제목" class="input-text">
			<input type="text" name="writer" placeholder="작성자" value="${ses.id }" readonly="readonly" class="input-text bg-gray">
			<textarea name="content" class="textarea-text"></textarea>
			<button type="submit" class="button-green">글쓰기</button>
			<a href="javascript:location.href=document.referrer">뒤로가기</a>
		</form>
	</div>
	
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