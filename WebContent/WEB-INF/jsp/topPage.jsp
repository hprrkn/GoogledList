<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ググったリスト</title>
	<link href="/GoogledList/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>

	<form class="form-horizontal"action="WordListServlet" method="post">
		<div class="form-group">
			<label for="name" class="col-xs-2 control-label">word</label>
			<div class="col-xs-4"><input type="text" name="addWord" class="form-control"></div>
		</div>
		<div class="form-group">
			<label for="name" class="col-xs-2 control-label">memo</label>
			<div class="col-xs-4"><input type="text" name="memo"class="form-control"></div>
		</div>
		<div class="form-group">
			<label for="name" class="col-xs-2 control-label">tag</label>
			<c:forEach var="allTagList" items="${allTagList}">
	            <label class="checkbox-inline">
 	               <input type="checkbox" name="tagId" value="${allTagList.tagId}">${allTagList.tagName} 
	            </label>
			</c:forEach>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">+tag:</label>
			<div class="col-xs-4"><input type="text" name="newTag" class="form-control"></div>
		</div>
		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-10">
				<input type="submit" value="add" class="btn btn-primary">
			</div>
		</div>
	</form>

	<ul class="list-group">
 		<c:forEach var="monthCntList" items="${monthCntList}">
	    	<li class="list-group-item">
	    		<a href = "/GoogledList/WordListServlet?date=${monthCntList.date}">
	    			${monthCntList.strYearMonth}　　<span class="badge">${monthCntList.cnt}</span>
    			</a>
	    	</li>
    	</c:forEach>
	</ul>
		
	
	<div class="panel panel-default">
	  <div class="panel-body">
		<a href="/GoogledList/TagListServlet">タグリスト</a>
	  </div>
	</div>
</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/GoogledList/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>