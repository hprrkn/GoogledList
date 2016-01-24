<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>タグリスト</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>
<a href="/TopPageServlt">戻る</a>
	<c:if test="${!empty msg}"><c:out value="${msg}"/></c:if><br>

	<p>タグリスト</p>
	<form method="GET" action="/GoogledList/EditTagServlet">
		<c:forEach var="allTagList" items="${allTagList}">
			<a href="/GoogledList/WordListByTagServlet?tagId=${allTagList.tagId}">${allTagList.tagName}</a><button type="submit" name="tagId" value="${allTagList.tagId}">編集</button>
		</c:forEach>
	</form>
	<form method="POST" action="/GoogledList/EditTagServlet">
		新規タグ：<input type="text" name="addTagName">
		<input type="submit" value="追加">
	</form>
	
</body>
</html>