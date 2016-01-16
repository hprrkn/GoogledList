<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>編集・削除</title>
</head>
<body>
	<p>編集</p>
	<form action="EditServlet" method="POST"><br>
		<input type="hidden" name="delete_flg" value="false">
		<input type="hidden"  name="id" value="${detail.id}">
		${detail.word}　→　<input type="text" name="editedword"><br>
		${detail.memo}　→　<input type="text" name="editedmemo"><br>
		<c:forEach var="allTagList" items="${allTagList}">
			<input type="checkbox" name="tagId" value="${allTagList.tagId}"
				<c:forEach var="tagList" items="${tagList}">
					<c:if test="${allTagList.tagId == tagList.tagId}"> checked="checked"</c:if>
				</c:forEach>
			>${allTagList.tagName} 
		</c:forEach><br>
		<input type="submit" value="ok"> 
	</form>

	<form action="EditServlet" method="POST"><br>
		<input type="hidden"  name="id" value="${detail.id}">
		<input type="hidden" name="delete_flg" value="true"><br>
		<input type="submit" value="delete">
	</form>

</body>
</html>