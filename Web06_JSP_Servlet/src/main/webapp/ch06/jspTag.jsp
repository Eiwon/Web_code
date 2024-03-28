<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Tag</title>
</head>
<body>
	<%-- Declaration(선언문) --%>
	<%! // 클래스 내부
		public int add(int x, int y){
			return x + y;
		}
	%>
	
	<%-- Scriptlet --%>
	<% // service() 메소드 내부
		// 지역 변수 선언
		int result = add(20, 30);
		
		// JSP에서 출력 방법
		// 1) 로그
		System.out.println("result = " + result);
		
		// 2) JspWriter 객체를 사용하여 응답(response)으로 출력
		out.write("<p>result = " + result + "</p>\r\n");
		// \r \n : 다음 출력 위치를 다음 줄(\n)의 시작지점(\r)으로 이동 
		
	%>
	<%-- 3) Expression 사용 --%>
	<p>결과 = <%=result%></p>
	
	<%
		Date date = new Date();
	%>
	
	<p><%=date %></p>
	
</body>
</html>





