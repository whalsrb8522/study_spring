<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Register Page</h1>
	
	<form action="/board/register" method="post" enctype="multipart/form-data">
		Title : <input type="text" name="title" placeholder="제목"> <br>
		Writer : <input type="text" name="writer" value="${ses.id }" placeholder="글쓴이" readonly="readonly"> <br>
		Content : <br>
		<textarea rows="20" cols="50" name="content"></textarea> <br>
		<input type="file" id="file" name="files" multiple="multiple" style="display: none">
		<button type="button" id="trigger">FileUpload</button> <br>
		<div id="fileZone">
		
		</div>
		<button type="submit" id="regBtn">글쓰기</button>
	</form>
	<a href="/board/list"><button type="button">목록으로</button></a>
	
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
</body>
</html>