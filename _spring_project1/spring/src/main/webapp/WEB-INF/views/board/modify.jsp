<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="bvo" value="${bdto.bvo }" />
	<c:set var="listFvo" value="${bdto.listFvo }" />
	<h1>Modify Page</h1>
	
	<form action="/board/modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bno" value="${bvo.bno }">
		Title : <input type="text" name="title" value="${bvo.title }" placeholder="제목"> <br>
		Writer : <input type="text" name="writer" value="${bvo.writer }" placeholder="글쓴이" readonly="readonly"> <br>
		Content : <br>
		<textarea rows="20" cols="50" name="content">${bvo.content }</textarea> <br>
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
						<button type="button" class="file-x" data-uuid="${fvo.uuid }">X</button>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		<input type="file" id="file" name="files" multiple="multiple" style="display: none">
		<button type="button" id="trigger">FileUpload</button> <br>
		<div id="fileZone">
		</div>
		<button type="submit" id="regBtn">수정</button> <br>
		<a href="/board/delete?bno=${bvo.bno }"><button type="button">삭제</button></a> <br>
		<a href="/board/list"><button type="button">목록으로</button></a>
	</form>
	
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<script type="text/javascript" src="/resources/js/boardModify.js"></script>
</body>
</html>