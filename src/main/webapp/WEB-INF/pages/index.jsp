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
	<form action="/login" method="POST">
		测试：<input type="text" name="jsonStr">
		<%--<br><br>--%>
		<input type="submit" value="登录">
    </form>
</body>
</html>

