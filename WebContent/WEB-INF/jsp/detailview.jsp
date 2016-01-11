<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${detail.word}</title>
</head>
<body>
	<p>ググったもの：${detail.word}</p><br>
	<p>メモ：${detail.memo}</p><br>
	<p>ググった日：${detail.added_day}</p><br>
	<p>タグ：
	<c:forEach var="tagList" items="${tagList}">
 		${tagList.tagName}
	</c:forEach> 
	<br>
	<form action="EditServlet" method="get">
		<input type="hidden" name="selectedId" value="${detail.id}"> 
		<input type="submit" value="編集・削除">
	</form>
</body>
</html>