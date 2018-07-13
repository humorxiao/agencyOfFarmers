<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/common/base.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>豐隆</title>
</head>

<body>
	<form action="/login1" method="POST">
		用户名:<br>
		<input type="text" name="admin_name">
		<br>
		密码:<br>
		<input type="text" name="admin_password">
		<br>
		cell:<br>
		<input type="text" name="admin_cell">
		<br>
		email:<br>
		<input type="text" name="admin_email">
		<br><br>
		<input type="submit" value="登录">
    </form>
</body>
</html>

