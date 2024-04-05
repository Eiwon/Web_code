<%@page import="java.util.ArrayList"%>
<%@page import="edu.web.board.PostVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<%
		List<PostVO> list = (List<PostVO>)request.getAttribute("postList");	
		if(list == null){ // 비정상 접근 = 게시판 데이터 얻으러 ㄱ
			list = new ArrayList<>();
			out.println("<script>location.href='board/list?type=search&page=1'</script>");
		}
	%>
	<div>
		<h1>게시판</h1>
	</div>
	<div>
		<table>
			<thead>
				<tr>
					<th>글 ID</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<% 	
					PostVO post;
					for(int i = 0; i < list.size(); i++){
						post = list.get(i);
						out.println("<tr id='" + post.getPostId() + "' onclick='toDetail(this);'><td>" + post.getPostId() + 
									"</td><td>" + post.getPostTitle() + 
									"</td><td>" + post.getUserId() + 
									"</td><td>" + post.getViews() + 
									"</td><td>" + post.getWriteDate() + "</td></tr>");
					}
				%>
			</tbody>
		</table>
	</div>
	<div>
		<%
			for(int i = 1; i < 11; i++){
				out.println("<a href='" + request.getContextPath() + "/board/list?type=search&page=" + i + "'>" + i + "</a>");
			}		
		%>
	</div>
	<div>
		<input type="button" value="글 쓰기" onclick="location.href='writePost.jsp';">
	
	</div>
	
	<script type="text/javascript">
		function toDetail(input){
			console.log(input.id);
			
			location.href="list?type=detail&postId=" + input.id;
		}
		
	</script>
	
</body>
</html>