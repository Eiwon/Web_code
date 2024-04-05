<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- param.(parameter name) == request.getParameter(parameter name) --%>
	
	<p>아이디 : ${param.userId }</p>
	<p>비밀번호 : ${param.password }</p>
	<p>이메일 : ${param.email }</p>
	<p>이메일 수신여부 : ${param.emailAgree }</p>
	<p>관심사항 : </p>
	<p>${paramValues.interest }</p>
	<p>${paramValues.interest[0] }</p>
	<p>${paramValues.interest[1] }</p>
	<p>${paramValues.interest[2] }</p>
	<p>${paramValues.interest[3] }</p>
	<p>${paramValues.interest[4] }</p>
	<p>핸드폰 : ${param.phone }</p>
	<p>자기소개 : ${param.introduce }</p>
	
</body>
</html>