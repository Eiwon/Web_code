<%@page import="edu.web.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Result</title>
</head>
<body>
	<%
		String msg = (String)request.getAttribute("msg");
		MemberVO userInfo = new MemberVO();
		
		if(msg != null){
		out.print("<script>alert('" + msg + "');</script>");
		request.removeAttribute("msg");
		}
		if(request.getAttribute("userInfo") == null){
			out.print("<script>location.href='select.do';</script>");
		}else{
			userInfo = (MemberVO)request.getAttribute("userInfo");
		}
	%>
	<div>
		<p>아이디 : <%=userInfo.getUserId() %></p>
		<p>패스워드 : <%=userInfo.getPassword() %></p>
      	<p>이메일 : <%=userInfo.getEmail() %></p>
      	<p>이메일 수신여부 : <%=userInfo.getEmailAgree() %></p>
      	<p>취미 : <%=userInfo.getInterestJoin() %></p>
		<p>핸드폰 : <%=userInfo.getPhone() %></p>
		<p>자기소개 : <%=userInfo.getIntroduce() %></p>
    </div>
    <div>
    	<button onclick="location.href='memberUpdate.jsp'">회원 수정</button>
    	<button onclick="location.href='delete.do'">회원 탈퇴</button>
    </div>

</body>
</html>