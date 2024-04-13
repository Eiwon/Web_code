<%@page import="edu.web.domain.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Detail</title>
</head>
<body>
	<%
		BoardVO board = (BoardVO)request.getAttribute("board");
	%>
	<div>
		<div>
			글 번호 : <span><%=board.getBoardId() %></span>
		</div>
		<div>
			<h2> 제목 : <%=board.getBoardTitle() %></h2>
		</div>
		<div>
			작성자 : <strong><%=board.getMemberId() %></strong>
		</div>
		<div>
			작성시간 : <span><%=board.getBoardDateCreated() %></span>
		</div>
		<div>
			<textarea rows="10" cols="30" readonly>
				<%=board.getBoardContent() %>
			</textarea>
		</div>
	</div>
	<div>
		<table>
			<tbody id="replyList" style="height: 100px;"></tbody>
		</table>
		<div>
			<input type="text" id="memberId" placeholder="작성자 ID">
			<input type="text" id="replyContent" placeholder="댓글 내용">
			<input type="button" id="btnWriteReply" value="댓글 쓰기">
		</div>
	</div>
	<div>
		<input type="button" value="수정하기" 
			onclick="location.href='board/update.do?boardId=' + '<%=board.getBoardId() %>'">
		<form action="board/delete.do" method="POST">
			<input type="text" name="boardId" value="<%=board.getBoardId() %>" hidden="">
			<input type="submit" value="삭제하기"> 
		</form>
		<input type="button" value="목록으로"
			onclick="location.href='index.jsp'">
	</div>
	
	<script type="text/javascript">
	
		let replyList = $('#replyList');
		
		$(document).ready(function(){
			getReplyList();
			
			$('#btnWriteReply').click(function(){
				writeReply();
			}); // end btnWriteReply.click
			
		}); // end document.ready
		
			
		function getReplyList(){
			console.log("getReplyList");
			$.ajax({
				type : "POST",
				url : "../reply/list.do",
				data : {
					"boardId" : "<%=board.getBoardId()%>"
				},
				success : function(result){
					console.log(result);
					let jsonRes = JSON.parse(result);
					let reply = "";
					replyList.html("");
					for(x in jsonRes){
						let writerBlock = $('<div></div>').text(jsonRes[x].memberId);
						let dateBlock = $('<div></div>').text(jsonRes[x].replyDateCreated);
						let contentBlock = $('<div></div>').text(jsonRes[x].replyContent);
						let btnDelete = $('<input type="button" value="X">').click(function(){
							deleteReply(this);
						});
						let hiddenReplyId = $('<p hidden=""></p>').text(jsonRes[x].replyId);
						let btnUpdate = $('<input type="button" value="수정">').click(function(){
							updateReply(this);	
						});
						let replyBlock = $('<div></div>');
						replyBlock.append(writerBlock);
						replyBlock.append(dateBlock);
						replyBlock.append(contentBlock);
						replyBlock.append(btnDelete);
						replyBlock.append(hiddenReplyId);
						replyBlock.append(btnUpdate);
						
						replyList.append(replyBlock);
					}
				}
			}); // end ajax
		} // end getReplyList
		
		function writeReply(){
			let content = $('#replyContent').val();
			let writer = $('#memberId').val();
			console.log("writeReply() : " + content);
			if(content.length == 0 || writer.length == 0) return;
			
			let reply = {
				"boardId" : "<%=board.getBoardId()%>",
				"memberId" : writer,
				"replyContent" : content
			};
			
			$.ajax({
				type : "POST",
				url : "../reply/register.do",
				data : {
					"reply" : JSON.stringify(reply)
				},
				success : function(result){
					console.log(result);
					console.log(typeof result);
					if(result == '1'){
						getReplyList();
						$('#replyContent').val("");
					}
				}
			}); // end ajax
		} // end writeReply
		
		function deleteReply(input){
			let replyId = $(input).next().text();
			console.log(replyId);
			
			$.ajax({
				type : "POST",
				url : "../reply/delete.do",
				data : {
					"replyId" : replyId
				},
				success : function(result){
					console.log(result);
					if(result == '1'){
						getReplyList();
					}
				}
			}); // end ajax
		} // end deleteReply
		
		function updateReply(input){
			let replyId = $(input).prev().text();
			let changed = prompt("변경할 내용 입력");
			console.log(changed);
			
			$.ajax({
				type : "POST",
				url : "../reply/update.do",
				data : {
					"replyId" : replyId,
					"replyContent" : changed
				},
				success : function(result){
					console.log(result);
					if(result == '1'){
						getReplyList();
					}
				}
			}); // end ajax
		} // end updateReply
		
	</script>
	
</body>
</html>