<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>タグ編集・削除</title>
	<link href="/GoogledList/Lib/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>

<div class="container">
	<div class="panel panel-primary">
	  <div class="panel-heading">
	  	<div class="col-xs-offset-2">
	  		Edit Tag
	  	</div>
	  </div>
	  <div class="panel-body">
		<form class="form-horizontal"action="TagListServlet" method="post">
			<div class="form-group">
				<label for="name" class="col-xs-2 control-label">word</label>
				<div class="col-xs-4"><input type="text" name="tagName" value="${tagDetail.tagName}" class="form-control"></div>
				<input type="hidden" name="delete_flg" value="false">
				<input type="hidden"  name="tagId" value="${tagDetail.tagId}">
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-10">
					<input type="submit" value="Edit" class="btn btn-primary">
				</div>
			</div>
		</form>
		<form  class="form-horizontal" action="TagListServlet" method="POST">
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-10">
					<input type="hidden"  name="tagId" value="${tagDetail.tagId}">
					<input type="hidden" name="delete_flg" value="true"><br>
					<input type="submit" value="delete" class="btn btn-primary">
				</div>
			</div>
		</form>	
	  </div>
	</div>
</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/GoogledList/Lib/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>