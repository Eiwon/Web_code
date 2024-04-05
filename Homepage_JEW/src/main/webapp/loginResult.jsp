<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>
</head>
<body>
	<%
		String userId = (String) request.getSession().getAttribute("userId");
	%>
	<div>
		<p><%=userId %>님 환영합니다.</p>
	</div>
	<button onclick="location.href='board/list?type=search'">게시판</button>
	<button onclick="location.href='select.do'">회원 정보</button>
	<button onclick="location.href='logout.do'">로그 아웃</button>
</body>
</html>