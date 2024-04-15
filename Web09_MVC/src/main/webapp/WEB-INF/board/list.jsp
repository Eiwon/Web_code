<%@page import="edu.web.util.PageMaker"%>
<%@page import="edu.web.domain.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
   border-style : solid;
   border-width : 1px;
   text-align : center;
}

ul {
   list-style-type : none;
}

li {
   display : inline-block;
}
</style>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<h1>게시판 메인</h1>
	<table>
		<thead>
			<tr>
				<th style="width: 60px;">ID</th>
				<th style="width: 700px;">제목</th>
				<th style="width: 120px;">작성자</th>
				<th style="width: 100px;">등록 시간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr onclick="location.href='detail.do?boardId=' + '${vo.boardId }'">
					<td>${vo.boardId }</td>
					<td>${vo.boardTitle }</td>
					<td>${vo.memberId }</td>
					<td>${vo.boardDateCreated }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul>
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list.do?page=${pageMaker.startPageNo -1 }">이전</a></li>
		</c:if>
		<c:forEach var="i" begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" >
			<li><a href="list.do?page=${i }">${i }</a></li>
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li><a href="list.do?page=${pageMaker.endPageNo +1 }">다음</a></li>
		</c:if>
	</ul>
	
	<input type="button" value="글 작성" onclick="location.href='register.do'">
	<input type="button" value="로그인" onclick="location.href='member/login'">
</body>
</html>