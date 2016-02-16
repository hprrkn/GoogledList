<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MyPage</title>
	<link href="/GoogledList/Lib/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>
  	<ul class="list-group">
		<li class="list-group-item">
			<span class="glyphicon glyphicon-search"></span> UserId : ${userMaster.userId}
		</li>
		<li class="list-group-item">
			<span class="glyphicon glyphicon-search"></span> RegisterDate : ${userMaster.registerDate}
		</li>
		<li class="list-group-item">
			<span class="glyphicon glyphicon-search"></span> TotalWordCount : ${wordCount}
		</li>
	</ul>
</div>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/GoogledList/Lib/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>