<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${strDate}のググったリスト</title>
</head>
<body>

<p>${strDate}"のググったリスト</p>
<c:forEach var="monthList" items="${monthWord}">
	${monthWord.word}/${monthWord.added_day}　　<form action="detailWordViewServlet" method="post"><input type="hidden" name="id" value="${nowMonthWord.id}"><input type="submit" value="detail"></form><br>
</c:forEach> 

</body>
</html>