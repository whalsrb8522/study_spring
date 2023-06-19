<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/list">
		<select name="type">
			<option value="t" ${typed eq 't' ? 'selected':''}>title</option>
			<option value="c" ${typed eq 'c' ? 'selected':''}>content</option>
			<option value="w" ${typed eq 'w' ? 'selected':''}>writer</option>
			<option value="tc" ${typed eq 'tc' ? 'selected':''}>title + content</option>
			<option value="tw" ${typed eq 'tw' ? 'selected':''}>title + writer</option>
			<option value="cw" ${typed eq 'cw' ? 'selected':''}>content + writer</option> 
		</select>
		<input type="text" name="keyword" placeholder="Search...">
		<button type="submit">검색</button>
	</form>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bvo" items="${listBvo }">
				<tr onclick="javascript:location.href='/board/detail?bno=${bvo.bno}'">
					<td>${bvo.bno }</td>
					<td>${bvo.title }</td>
					<td>${bvo.writer }</td>
					<td>${bvo.readcount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${ph.prev }">
		<a href="/board/list?pageNo=${ph.startPage - 1 }&qty=${ph.pvo.qty }&type=${ph.pvo.type}&keyword=${ph.pvo.type }">◀</a>
	</c:if>
	
	<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		<a href="/board/list?pageNo=${i }&qty=${ph.pvo.qty }&type=${ph.pvo.type}&keyword=${ph.pvo.type }">${i }</a>
	</c:forEach>
	
	<c:if test="${ph.next }">
		<a href="/board/list?pageNo=${ph.endPage + 1 }&qty=${ph.pvo.qty }&type=${ph.pvo.type}&keyword=${ph.pvo.type }">▶</a>
	</c:if>
	
	<br>

	<a href="/board/register"><button type="button">글쓰기</button></a>
	<a href="/"><button type="button">메인으로</button></a> <br>
</body>
</html>