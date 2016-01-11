<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${strDate}のググったリスト</title>
</head>
<body>

<c:if test="${!empty msg}"><c:out value="${msg}"/></c:if>

<p>${strDate}のググったリスト</p>
<c:forEach var="wordList" items="${wordList}">
	<a href="/GoogledList/detailWordViewServlet?id=${wordList.id}">${wordList.word}/${wordList.added_day}</a><br>
</c:forEach> 

</body>
</html>