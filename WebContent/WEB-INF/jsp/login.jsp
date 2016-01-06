<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login/Register</title>
</head>
<body>
<c:if test="${!empty msg}"><p><c:out value="${msg}"/></p><br></c:if>

<p>ログイン</p>
<form action=LoginServlet method=post>
	ユーザーネーム：<input type=text name=userName>
	パスワード：<input type=text name=userPass>
	<input type=submit name=Login value="GO">
</form>
<p>新規登録</p>
<form action=RegisterServlet method=post>
	ユーザーネーム：<input type=text name=userName>
	パスワード：<input type=text name=userPass>
	<input type=submit name=Register value="GO">
</form>
</body>
</html>