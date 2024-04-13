<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	전송된 데이터가 'a'로 시작하면 "apple"
	'b'로 시작하면 "banana"
	'c'로 시작하면 "coconut"
	그 외 "not fruits" 를 출력
--%>
<c:set var="name" value="${param.name}"/>
<c:choose>
	<c:when test="${name.equals('a')}">
		apple
	</c:when>
	<c:when test="${name.equals('b')}">
		banana
	</c:when>
	<c:when test="${name.equals('c')}">
		coconut
	</c:when>
	<c:otherwise>
		not fruits
	</c:otherwise>
</c:choose>




