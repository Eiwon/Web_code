<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<body>
	<%
		String msg = (String)request.getAttribute("msg");
		String path = (String)request.getAttribute("path");
		System.out.println(path);
		
		if(msg != null && path != null){
			request.removeAttribute("msg");
			request.removeAttribute("path");
			out.print("<script>alert('" + msg + "');</script>");
			out.print("<script>location.href='" + getServletContext().getContextPath()
					+ path + "';</script>");
		}else{
			out.print("<script>location.href='" + getServletContext().getContextPath()
					+ "/memberResult';</script>");
		}
	%>

</body>
</html>