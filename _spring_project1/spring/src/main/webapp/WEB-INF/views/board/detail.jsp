<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="bvo" value="${bdto.bvo}" />
	<c:set var="listFvo" value="${bdto.listFvo }" />

	<h1>Detail Page</h1>

	Title : ${bvo.title } <br>
	Writer : ${bvo.writer }	<br>
	Content : ${bvo.content } <br>
	<div>
		<ul>
			<c:forEach var="fvo" items="${listFvo }">
				<li>
					<c:choose>
						<c:when test="${fvo.file_type > 0 }">
							<img src="/upload/${fn:replace(fvo.save_dir, '\\', '/') }/${fvo.uuid }_th_${fvo.file_name }">
						</c:when>
					</c:choose>
					<br>
					(${fvo.reg_date })
				</li>
			</c:forEach>
		</ul>
	</div>
	<c:if test="${ses != null && ses.id == bvo.writer}">
		<a href="/board/modify?bno=${bvo.bno }"><button type="button">수정</button></a> <br>
	</c:if>
	<a href="javascript:history.back()"><button type="button">목록으로</button></a>
	
	<h1>Comment Page</h1>
	<div>
		<!-- 댓글 작성 라인 -->
		<div>
			<span id="cmtWriter"> ${ses.id }</span>
			<input type="text" id="cmtText" placeholder="Test Add Comment">
			<button type="button" id="cmtPostBtn">Post</button>
		 </div>
		
		<!-- 댓글 표시 라인 -->
		<div>
			<!-- li 하나가 하나의 댓글 객체 -->
			<ul id="cmtListArea">
				<li>
					<div>
					<div>Writer</div>
						Content for Comment
					</div>
					<span>mod_date</span>
				</li>
			</ul>
		</div>
	</div>
	
	<script type="text/javascript">
		const bnoVal = '<c:out value="${bvo.bno}" />';
		// console.log("detail.jsp > bnoVal : " + bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
		getCommentList(bnoVal)	
	</script>
</body>
</html>