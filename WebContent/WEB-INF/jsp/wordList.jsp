<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		<c:if test="${!empty addWord}">今月のググったリスト</c:if>
		<c:if test="${empty addWord}">${strDate}のググったリスト</c:if>
	</title>
	<link href="/GoogledList/Lib/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>

<c:if test="${!empty msg}">
	<div class="alert alert-warning alert-dismissible" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <c:out value="${msg}"/>
	</div>
</c:if>
<c:if test="${!empty addedWord}">
	<div class="alert alert-warning alert-dismissible" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <strong>${addedWorded.word}</strong>を追加しました。
	  <c:if test="${!empty addedWord.memo}"><p>　memo:<strong>${addedWord.memo}</strong></p></c:if>
	</div>
</c:if>

<div class="container">
	<div class="panel panel-primary">
	  <div class="panel-heading">
		<c:if test="${!empty addedWord}"><p>今月のググったリスト</p></c:if>
		<c:if test="${empty addedWord}"><p>${strDate}のググったリスト</p></c:if>
	  </div>
	  <div class="panel-body">
	  	<ul class="list-group">
			<c:forEach var="wordList" items="${wordList}">
				<li class="list-group-item">
					<span class="glyphicon glyphicon-search"></span><a href="/GoogledList/WordDetailServlet?id=${wordList.id}"><strong>　${wordList.word}</strong></a>
					<p class="text-right">${wordList.added_day}</p>
				</li>
		  	</c:forEach> 
		</ul>
	  </div>
	</div>
</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/GoogledList/Lib/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>