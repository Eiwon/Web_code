<%@page import="edu.web.domain.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
		System.out.println(list.toString());
	%>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록 시간</th>
			</tr>
		</thead>
		<tbody>
			<% for(BoardVO board : list){ %>
				<tr onclick="location.href='detail.do?boardId=' + '<%=board.getBoardId() %>'">
					<td><%=board.getBoardId() %></td>
					<td><%=board.getBoardTitle() %></td>
					<td><%=board.getMemberId() %></td>
					<td><%=board.getBoardDateCreated() %></td>
				</tr>
			<% }%>
		</tbody>
	</table>
	<input type="button" value="글 작성" onclick="location.href='register.do'">
</body>
</html>