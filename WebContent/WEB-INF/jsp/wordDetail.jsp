<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${detail.word}</title>
	<link href="/GoogledList/Lib/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>
<div class="container">
	<div class="well">
	<table class="table table-bordered">
  		<tr><td class="col-xs-2">GoogledWord</td><td><strong>${detail.word}</strong></td></tr>
  		<tr><td class="col-xs-2">Memo</td><td><strong>${detail.memo}</strong></td></tr>
  		<tr><td class="col-xs-2">GoogledDay</td><td><strong>${detail.added_day}</strong></td></tr>
  		<tr><td class="col-xs-2">Tag</td>
  		<td>		
	  		<c:forEach var="tagList" items="${tagList}">
		 		<strong>${tagList.tagName}</strong>
			</c:forEach>
		</td></tr>
	</table>
		<form action="EditWordServlet" method="get">
			<input type="hidden" name="selectedId" value="${detail.id}"> 
			<input type="submit" value="Edit/Delete" class="btn btn-primary">
		</form>
	</div>
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/GoogledList/Lib/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>