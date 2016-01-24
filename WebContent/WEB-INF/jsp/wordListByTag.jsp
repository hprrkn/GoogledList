<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${tag.tagName}のワードリスト</title>
</head>
<body>
<c:forEach var="wordList" items="${wordList}">
	<a href="/GoogledList/WordDetailServlet?id=${wordList.id}">${wordList.word}/${wordList.added_day}</a><br>
</c:forEach>

</body>
</html>