<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력 정보</title>
</head>
<body>
	<%=request.setCharacterEncoding("UTF-8") %>
	<jsp:useBean id="member" class="edu.web.homework.MemberVO" />
	<jsp:setProperty property="*" name="member" />
	<h1>입력 정보</h1>
	<div>
		<ul>
			<li> 아이디 : <%=member.getUserid() %></li>
			<li> 비밀번호 : <%=member.getPassword() %></li>
			<li> 이메일 : <%=member.getEmail() %></li>
			<li> 이메일 수신여부 : <%=member.getEmailAgree() %></li>
			<li> 관심사항 : <%=member.getInterestToString() %></li>
			<li> 핸드폰 : <%=member.getPhone() %></li>
			<li> 자기소개 : <%=member.getIntroduce() %></li>
		</ul>
	</div>
</body>
</html>




