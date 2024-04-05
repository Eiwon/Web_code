<%@page import="edu.web.board.PostVO"%>
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
		PostVO post = (PostVO)request.getAttribute("postInfo");
	%>
	<div>
		<p>글 ID : <%=post.getPostId() %></p><br>
		<p>조회수 : <%=post.getViews() %></p>
	</div>
	<div>
		<strong><%=post.getPostTitle() %></strong>
	</div>
	<div>
		<textarea rows="30" cols="60" readonly="readonly">
			<%=post.getPostContent() %>
		</textarea>
	</div>
	<div>
		<p>작성자 : <%=post.getUserId() %></p>
	</div>
	<div>
		<p>작성 시간 : <%=post.getWriteDate() %></p>
	</div>
	
	<div>
	
	<form action="list" method="POST">
		<input type="submit" name="type" value="수정하기">
		<input type="text" name="postId" value="<%=post.getPostId() %>" hidden>
		<input type="text" name="writerId" value="<%=post.getUserId() %>" hidden> 
	</form>
		
	</div>
	
	
</body>
</html>