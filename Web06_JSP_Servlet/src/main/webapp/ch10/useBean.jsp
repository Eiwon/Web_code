<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UseBean</title>
</head>
<body>
	<jsp:useBean id="member" class="web.ch10.bean.MemberBean"></jsp:useBean>
	
	<%-- useBean 사용법 1 --%>
	<jsp:setProperty property="userId" value="guest" name="member"/>
	
	<p>id 출력 : <jsp:getProperty property="userId" name="member"/></p>

	<%-- useBean 사용법 2 : getter / setter 이용 --%>

	<% member.setEmail("test@test.com"); %>
	<p>email 출력 : <%=member.getEmail() %></p>

</body>
</html>




