<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Test</title>
</head>
<body>
	<%
		String userId = "";
		if(session.getAttribute("userId") != null){
			userId = (String)session.getAttribute("userId");
		}
		
	%>

	<h2><%=userId %>님 환영합니다.</h2>
</body>
</html>