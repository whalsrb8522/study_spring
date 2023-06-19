<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<style type="text/css">
	#innerContainer {
		width: 900px;
		flex-direction: column;
	}
	
	#innerContainer .button-sm {
		width: 150px
	}
	
	table {
		width: 100%;
		border-collapse: collapse;
	}
	
	table thead th {
		border-bottom: 1px solid gray;
		padding-bottom: 10px;
	}

	table tbody tr:hover {
		background-color: #eee;	
	}
	
	table tbody td {
		padding: 5px 0;
	}
	
	.selected {
		color: #03c75a;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div id="container">
		<div id="innerContainer" class="border-gray">
			<table>
				<thead>
					<tr>
						<th width="100px">번호</th>
						<th>제목</th>
						<th width="180px">작성자</th>
						<th width="100px">조회수</th>
						<th width="180px">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bvo" items="${listBvo }">
						<tr onclick="javascript:location.href='/board/detail?bno=${bvo.bno}'">
							<td>${bvo.bno }</td>
							<td>${bvo.title }</td>
							<td>${bvo.writer }</td>
							<td>${bvo.readcount }</td>
							<td>${bvo.reg_date }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div>			
				<c:if test="${ph.prev }">
					<a href="/board/list?pageNo=${ph.startPage - 1 }&qty=${ph.pvo.qty }">◀</a>
				</c:if>
				
				<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
					<a href="/board/list?pageNo=${i }&qty=${ph.pvo.qty }" <c:if test="${i eq ph.pvo.pageNo }">class="selected"</c:if>>${i }</a>
				</c:forEach>
				
				<c:if test="${ph.next }">
					<a href="/board/list?pageNo=${ph.endPage + 1 }&qty=${ph.pvo.qty }">▶</a>
				</c:if>
			</div>
			
			<div>
				<button onclick="location.href='/board/register'" class="button-sm button-green">글쓰기</button>
			</div>
			<a href="/">홈으로</a>
		</div>
	</div>
</body>
</html>