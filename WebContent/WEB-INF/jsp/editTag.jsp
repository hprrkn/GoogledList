<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>タグ編集・削除</title>
	<link href="/GoogledList/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>

	<p>タグ編集</p>
	<form action="TagListServlet" method="POST"><br>
		<input type="hidden" name="delete_flg" value="false">
		<input type="hidden"  name="tagId" value="${tagDetail.tagId}">
		${tagDetail.tagName}　→　<input type="text" name="tagName" value="${tagDetail.tagName}"><br>
		<input type="submit" value="Edit"> 
	</form>

	<form action="TagListServlet" method="POST"><br>
		<input type="hidden"  name="tagId" value="${tagDetail.tagId}">
		<input type="hidden" name="delete_flg" value="true"><br>
		<input type="submit" value="delete">
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/GoogledList/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>