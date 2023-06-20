<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<c:set var="bvo" value="${bdto.bvo }" />
	<c:set var="listFvo" value="${bdto.listFvo }" />
	
	<div id="container">
		<form onsubmit="return modifyCheck()" action="/board/modify" method="post" name="form" id="innerContainer" class="border-gray input-title">
			<input type="hidden" name="bno" value="${bvo.bno }">
			<p>제목</p>
			<input type="text" name="title" value="${bvo.title }" class="input-text">
			<p>작성자</p>
			<input type="text" name="writer" value="${bvo.writer}" readonly="readonly" class="input-text bg-gray">
			<p>내용</p>
			<textarea name="content" class="textarea-text border-gray">${bvo.content}</textarea>
			<p>첨부파일</p>
			<c:if test="${listFvo ne null }">
				<ul>
					<c:forEach var="fvo" items="${listFvo }">
						<li>
							<c:choose>
								<c:when test="${fvo.file_type > 0 }">
									<img src="/upload/${fvo.save_dir }/${fvo.uuid}_th_${fvo.file_name}">
								</c:when>
							</c:choose>
							${fvo.file_name } (<span>${fvo.file_size } bytes</span>)
							<button type="button" class="file-x button-red" data-uuid="${fvo.uuid }">X</button>
						</li>
					</c:forEach>
				</ul>
			</c:if>
			<input type="file" id="file" name="files" multiple="multiple" style="display: none">
			<button type="button" id="trigger" class="button-green">파일선택</button> <br>
			<div id="fileZone">
			
			</div>
			<button type="submit" class="button-green">글수정</button>
			<a href="/board/remove?bno=${bvo.bno }">글삭제</a>
			<a href="javascript:location.href=document.referrer">뒤로가기</a>
		</form>
	</div>
	
	<script type="text/javascript" src="/resources/js/board_register.js"></script>
	<script type="text/javascript" src="/resources/js/board_modify.js"></script>
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