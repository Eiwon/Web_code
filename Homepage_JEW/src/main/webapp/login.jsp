<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
</head>
<body>
	<%
		String msg = (String)request.getAttribute("msg");
		if(msg != null){
			out.print("<script>alert('" + msg + "');</script>");
		}
	%>
	<form action="loginAuth.do" method="POST">
		<p>ID</p>
		<input type="text" required="required" name="userId">
		<p>비밀번호</p>
		<input type="password" required="required" name="password">
	
		<input type="submit" value="로그인">
	</form>
	<a href="memberRegister.jsp">회원가입</a>
	
</body>
</html>