<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addedList</title>
</head>
<body>

<div class="addedWordComfirm">
<h6>${addword}を追加しました。</h6>
<p>memo:${memo}</p>
</div>

<div class="now_month_list">
	<div class="list_view">
 	<c:forEach var="nowMonthWord" items="${nowMonthList}">
		${nowMonthWord.id}/${nowMonthWord.word}/${nowMonthWord.memo}/${nowMonthWord.added_day}<br>
	</c:forEach> 
	</div>
</div>

</body>
</html>