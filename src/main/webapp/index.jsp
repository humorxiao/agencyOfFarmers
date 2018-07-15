<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@include file="common/base.jsp" %>--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>豐隆</title>
</head>

<body>
	<form action="/login2" method="POST">
		用户名:<br>
		<input type="text" name="userName">
		<br>
		密码:<br>
		<input type="text" name="password">
		<br><br>
		<input type="submit" value="登录">
    </form>
</body>
</html>

