<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Directive</title>
</head>
<body>
	<%-- header.jspf 파일을 include --%>
	
	<h1>include directive(지시자)</h1>
	<%@ include file="header.jspf" %>
	
	
	<div>
		<p>본문입니다.</p>
	</div>
	
	<%@ include file="footer.jspf" %>
	<p><%=header %></p><%-- header.jspf 변수 사용 가능 --%>
	
	<%-- 
	* directive include 특징
	1. JSP 파일이 자바 파일로 바뀔 때 문서에 통합되어 컴파일(정적)
	2. 다수의 JSP 페이지에서 공통으로 사용되는 코드 작성 용도
	3. 포함되는 JSP 파일에서 선언한 변수들을, 포함하는 JSP 파일에서 사용가능.
	 --%>
	
</body>
</html>