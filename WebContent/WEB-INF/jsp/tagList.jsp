<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>タグリスト</title>
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

	<div class="container">
	<div class="panel panel-primary">
	  <div class="panel-heading">
		<h4>TagList</h4>
	  </div>
	  <div class="panel-body">
	  <form method="GET" action="/GoogledList/EditTagServlet">
	  	<ul class="list-group">
			<c:forEach var="allTagList" items="${allTagList}">
				<li class="list-group-item">
					<span class="glyphicon glyphicon-search"></span><a href="/GoogledList/WordListByTagServlet?tagId=${allTagList.tagId}"><strong>　${allTagList.tagName}</strong></a>
					<p class="text-right"><button type="submit" class="btn btn-primary" name="tagId" value="${allTagList.tagId}">Edit</button></p>
				</li>
		  	</c:forEach> 
		</ul>
		</form>
			<form method="POST" action="/GoogledList/EditTagServlet">
				NewTag：<input type="text" name="addTagName">
			<input type="submit" value="Add" class="btn btn-primary">
		</form>
	  </div>
	</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/GoogledList/Lib/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>