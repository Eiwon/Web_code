<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="member" class="edu.web.homework.MemberVO" />
	<jsp:setProperty property="*" name="member" />
	<div>
		<p>아이디</p>
		<%=member.getUserid() %>
		<p>비밀번호</p>
		<%=member.getPassword() %>
		<p>이메일</p>
		<%=member.getEmail() %>
		<p>이메일 수신여부</p>
		<%=member.getEmailAgree() %>
		<p>관심사항</p>
		<% String[] interest = member.getInterest(); 
			if(interest != null){
				for(int i=0; i< interest.length; i++){%>
			<p><%= (i+1) + ".   " + interest[i]%></p>
		<%	}}%>
		<p>핸드폰</p>
		<%=member.getPhone() %>
		<p>자기소개</p>
		<%=member.getIntroduce() %>
	
	</div>
	
</body>
</html>
