<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>
	<h2>글 작성</h2>
	<form action="register.do" method="POST">
		<div>
			제목
			<input type="text" name="boardTitle" required>
		</div>
		<div>
			내용
			<textarea rows="10" cols="30" name="boardContent" required></textarea>
		</div>
		<div>
			작성자
			<input type="text" name="memberId" required>
		</div>
		<input type="submit" value="등록">
	</form>
</body>
</html>