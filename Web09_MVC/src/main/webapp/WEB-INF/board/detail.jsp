<%@page import="edu.web.domain.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Detail</title>
</head>
<body>
	<div>
		<div>
			글 번호 : <span>${board.boardId }</span>
		</div>
		<div>
			<h2> 제목 : ${board.boardTitle }</h2>
		</div>
		<div>
			작성자 : <strong>${board.memberId }</strong>
		</div>
		<div>
			작성시간 : <span>${board.boardDateCreated }</span>
		</div>
		<div>
			<textarea rows="10" cols="30" readonly>
				${board.boardContent }
			</textarea>
		</div>
	</div>
	<div>
		<table>
			<tbody id="replyList" style="height: 100px; text-align: center;"></tbody>
		</table>
		<div id="replyLink" style="text-align: center; display: flex;"></div>
		<div>
			<input type="text" id="memberId" placeholder="작성자 ID">
			<input type="text" id="replyContent" placeholder="댓글 내용">
			<input type="button" id="btnWriteReply" value="댓글 쓰기">
		</div>
	</div>
	<div>
		<input type="button" value="수정하기" 
			onclick="location.href='update.do?boardId=' + '${board.boardId }'">
		<form action="delete.do" method="POST">
			<input type="hidden" name="boardId" value="${board.boardId }">
			<input type="submit" value="삭제하기"> 
		</form>
		<input type="button" value="목록으로"
			onclick="location.href='index.jsp'">
	</div>
	
	<script type="text/javascript">
	
		let replyList = $('#replyList');
		
		$(document).ready(function(){
			getReplyList(1);
			
			$('#btnWriteReply').click(function(){
				writeReply();
			}); // end btnWriteReply.click
			
		}); // end document.ready
		
			
		function getReplyList(page){
			console.log("getReplyList");
			$.ajax({
				type : "POST",
				url : "replies/list",
				data : {
					"boardId" : "${board.boardId }",
					"page" : page
				},
				success : function(result){
					console.log(result);
					let jsonRes = JSON.parse(result);
					let list = JSON.parse(jsonRes.replyList);
					let pageMaker = JSON.parse(jsonRes.pageMaker);
					
					replyList.html("");
					for(x in list){
						let writerBlock = $('<div></div>').text(list[x].memberId);
						let dateBlock = $('<div></div>').text(list[x].replyDateCreated);
						let contentBlock = $('<div></div>').text(list[x].replyContent);
						let btnDelete = $('<input type="button" value="X">').click(function(){
							deleteReply(this);
						});
						let hiddenReplyId = $('<p hidden=""></p>').text(list[x].replyId);
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
					
					let replyLink = $('#replyLink');
					replyLink.html("");
					if(pageMaker.hasPrev){
						replyLink.append("<p onclick='getReplyList(" + (pageMaker.startPageNo -1) + ")'>이전&nbsp</p>");
					}
					for(let x = pageMaker.startPageNo; x <= pageMaker.endPageNo; x++){
						replyLink.append("<p onclick='getReplyList(" + x + ")'>" + x + "&nbsp</p>");
					}
					if(pageMaker.hasNext){
						replyLink.append("<p onclick='getReplyList(" + (pageMaker.endPageNo +1) + ")'>다음</p>");
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
				"boardId" : "${board.boardId }",
				"memberId" : writer,
				"replyContent" : content
			};
			
			$.ajax({
				type : "POST",
				url : "replies/add",
				data : {
					"reply" : JSON.stringify(reply)
				},
				success : function(result){
					console.log(result);
					console.log(typeof result);
					if(result == '1'){
						getReplyList(1);
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
				url : "replies/delete",
				data : {
					"replyId" : replyId
				},
				success : function(result){
					console.log(result);
					if(result == '1'){
						getReplyList(1);
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
				url : "replies/update",
				data : {
					"replyId" : replyId,
					"replyContent" : changed
				},
				success : function(result){
					console.log(result);
					if(result == '1'){
						getReplyList(1);
					}
				}
			}); // end ajax
		} // end updateReply
		
	</script>
	
</body>
</html>