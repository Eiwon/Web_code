<%@page import="edu.web.domain.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
</head>
<body>
	<h2>글 수정</h2>
	<form action="update.do" method="POST">
		<div>
			글 번호
			<input type="text" name="boardId" value="${board.boardId }" readonly>
		</div>
		<div>
			제목
			<input type="text" name="boardTitle" value="${board.boardTitle }" required>
		</div>
		<div>
			내용
			<textarea rows="10" cols="30" name="boardContent" required>
				${board.boardContent }
			</textarea>
		</div>
		<div>
			작성자
			<input type="text" name="memberId" value="${board.memberId }" readonly>
		</div>
		<input type="submit" value="등록">
	</form>
</body>
</html>