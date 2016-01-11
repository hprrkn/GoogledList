<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ググったリスト</title>
</head>
<body>
	<form action="MonthListViewServlet" method="post">
		word:<input type="text" name="addWord"><br>
		memo:<input type="text" name="memo"><br>
		tag:
		<c:forEach var="allTagList" items="${allTagList}">
			<input type="checkbox" name="tagId" value="${allTagList.tagId}">${allTagList.tagName}
		</c:forEach><br>
		<input type="submit" value="add">
	</form>
	<br>
	<div class="month-view-list">
		<div class="month-view">
		 	<c:forEach var="monthCntList" items="${monthCntList}">
		 		<a href = "/GoogledList/MonthListViewServlet?date=${monthCntList.date}">${monthCntList.strYearMonth}:${monthCntList.cnt}</a><br>
			</c:forEach> 
		</div>
	</div>
</body>
</html>