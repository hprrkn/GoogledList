<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ググったリスト</title>
</head>
<body>
	<c:if test="${!empty msg}"><c:out value="${msg}"/></c:if>
	<form action="MonthListViewServlet" method="post">
		<input type="text" name="addWord">
		<input type="text" name="memo">
		<input type="submit" value="add">
	</form>
	<div class="month-view-list">
		<div class="month-view">
		 	<c:forEach var="wordlist" items="${wordlist}">
			 	${wordlist.word}/${wordlist.memo}/${wordlist.added_day}<br>
			</c:forEach> 
		</div>
	</div>
</body>
</html>