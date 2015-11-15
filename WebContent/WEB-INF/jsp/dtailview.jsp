<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${detail.word}</title>
</head>
<body>
ググったもの：${detail.word}<br>
メモ：${detail.memo}<br>
ググった日：${detail.added}<br>
<br>
<form action="editServlet" method="post">
<input type="hidden" name="selectedId" value="${detail.id}"> 
<input type="submit" value="編集・削除">
</form>
</body>
</html>