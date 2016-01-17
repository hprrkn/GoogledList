<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>タグ編集</title>
</head>
<body>
	<c:if test="${!empty msg}"><c:out value="${msg}"/></c:if><br>

	<p>タグ編集</p>
	<form method="GET" action="/GoogledList/EditTagServlet">
		<c:forEach var="allTagList" items="${allTagList}">
			<button type="submit" name="tagId" value="${allTagList.tagId}">${allTagList.tagName}</button>
		</c:forEach>
	</form>
	
</body>
</html>