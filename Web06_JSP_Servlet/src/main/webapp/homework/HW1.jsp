<%@page import="java.util.Date"%>
<%@page import="edu.web.homework.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HW1</title>
     <style type="text/css">
     table {
         font-family : arial, sans-serif;
         border-collapse : collapse;
         width : 100%;
     }
     td, th {
            border : 1px solid #dddddd;
         text-align : left;
         padding : 8px;
     }
     </style>
</head>
<body>
	<%
		ArrayList<BoardVO> list = new ArrayList<>();
		//int boardId, String boardTitle, String userId, Date boardRegDate
		// 게시글 데이터 5개를 list에 추가
		for(int i=0; i< 5; i++){
			list.add(new BoardVO(i, "Title" +i, "Id"+i, new Date()));
		}
	%>	
	
	<!-- table을 생성하여 5개의 게시글 출력 -->
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>제목</th>
				<th>작성자 ID</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<%for(int i = 0; i < list.size(); i++){%>
			<tr>
				<td><%=list.get(i).getBoardId() %></td>
				<td><%=list.get(i).getBoardTitle() %></td>
				<td><%=list.get(i).getUserId() %></td>
				<td><%=list.get(i).getBoardRegDate() %></td>
			</tr>
			<%} %>
		</tbody>
	</table>



</body>
</html>



