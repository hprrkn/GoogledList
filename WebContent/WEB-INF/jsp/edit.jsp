<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>編集・削除</title>
</head>
<body>
	<p>編集</p>
	<form action="EditedServlet" method="GET"><br>
		<input type="hidden" name="delete_flg" value="false">
		<input type="hidden"  name="id" value="${googledWord.id}">
		${googledWord.word}　→　<input type="text" name="editedword"><br>
		${googledWord.memo}　→　<input type="text" name="editedmemo"><br>
		<input type="submit" value="ok"> 
	</form>

	<form action="EditedServlet" method="GET"><br>
		<input type="hidden"  name="id" value="${googledWord.id}">
		<input type="hidden" name="delete_flg" value="true"><br>
		<input type="submit" value="delete">
	</form>

</body>
</html>