<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<c:if test="${!empty addWord}"><p>今月のググったリスト</p></c:if>
	<c:if test="${empty addWord}"><p>${strDate}のググったリスト</p></c:if>
</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>

<c:if test="${!empty msg}"><c:out value="${msg}"/></c:if>
<c:if test="${!empty addWord}"><h6>${addWord}を追加しました。</h6></c:if>
<c:if test="${!empty memo}"><p>memo:${memo}</p></c:if>

<c:if test="${!empty addWord}"><p>今月のググったリスト</p></c:if>
<c:if test="${empty addWord}"><p>${strDate}のググったリスト</p></c:if>
<c:forEach var="wordList" items="${wordList}">
	<a href="/GoogledList/WordDetailServlet?id=${wordList.id}">${wordList.word}/${wordList.added_day}</a><br>
</c:forEach> 

</body>
</html>