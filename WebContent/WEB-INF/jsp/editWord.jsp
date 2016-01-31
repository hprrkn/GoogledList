<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>編集・削除</title>
	<link href="/GoogledList/Lib/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/components/header.jsp"></jsp:include>
<div class="container">
	<div class="panel panel-primary">
	  <div class="panel-heading">
	  	<div class="col-xs-offset-2">
	 		Edit Word
	  	</div>
	  </div>
	  <div class="panel-body">
		<form class="form-horizontal"action="EditWordServlet" method="post">
			<div class="form-group">
				<label for="name" class="col-xs-2 control-label">word</label>
				<div class="col-xs-4"><input type="text" name="editedword" value="${detail.word}" class="form-control"></div>
			</div>
			<div class="form-group">
				<label for="name" class="col-xs-2 control-label">memo</label>
				<div class="col-xs-4"><input type="text" name="editedmemo" value="${detail.memo}" class="form-control"></div>
				<input type="hidden" name="delete_flg" value="false">
				<input type="hidden"  name="id" value="${detail.id}">
			</div>
			<div class="form-group">
				<label for="name" class="col-xs-2 control-label">tag</label>
				<c:forEach var="allTagList" items="${allTagList}">
		            <label class="checkbox-inline">
						<input type="checkbox" name="tagId" value="${allTagList.tagId}"
							<c:forEach var="tagList" items="${tagList}">
								<c:if test="${allTagList.tagId == tagList.tagId}"> checked="checked"</c:if>
							</c:forEach>
						>${allTagList.tagName}  
		            </label>
				</c:forEach>
			</div>
			<div class="form-group">
				<label class="col-xs-2 control-label">+tag:</label>
				<div class="col-xs-4"><input type="text" name="newTag" class="form-control"></div>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-10">
					<input type="submit" value="Edit" class="btn btn-primary">
				</div>
			</div>
		</form>
		<form  class="form-horizontal" action="EditWordServlet" method="POST">
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-10">
					<input type="hidden"  name="id" value="${detail.id}">
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