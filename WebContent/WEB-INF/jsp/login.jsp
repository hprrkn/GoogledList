<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login/Register</title>
	<link href="/GoogledList/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#login">Login</a></li>
		<li><a data-toggle="tab" href="#register">Register</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="login">
	        <div class="page-header">
	      		    <h1>Login</h1>
	      	</div>
			<form action=LoginServlet method=post class="form-horizontal">
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">name</label>
					<div class="col-xs-4"><input type="text" name="userName"class="form-control"></div>
				</div>
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">password</label>
					<div class="col-xs-4"><input type="text" name="userPass"class="form-control"></div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-xs-10">
						<input type="submit" name="Register" value="Go" class="btn btn-primary">
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane" id="register">
	        <div class="page-header">
	      		    <h1>Register</h1>
	      		</div>
			<form action=RegisterServlet method=post class="form-horizontal">
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">name</label>
					<div class="col-xs-4"><input type="text" name="userName"class="form-control"></div>
				</div>
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">password</label>
					<div class="col-xs-4"><input type="text" name="userPass"class="form-control"></div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-xs-10">
						<input type="submit" name="Register" value="Go" class="btn btn-primary">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
	<script type="text/javascript">
		jQuery(document).ready(function ($) {
		$('#tabs').tab();
		});
　　</script>
    <script src="/GoogledList/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>