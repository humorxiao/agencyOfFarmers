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
	<form action="/getAllUnionInfo" method="POST">
		测试getAllUnionInfo：<input type="text" name="jsonStr">
		<br><br>
		<input type="submit" value="登录">
    </form>
    <br><br>
    <form action="/addUnionInfo" method="POST">
        测试addUnionInfo：<input type="text" name="jsonStr">
        <br><br>
        <input type="submit" value="登录">
    </form>
    <br><br>
    <form action="/deleteUnionStaff" method="POST">
        测试deleteUnionInfo：<input type="text" name="jsonStr">
        <br><br>
        <input type="submit" value="登录">
    </form>
    <br><br>
    <form action="/updateUnionInfo" method="POST">
        测试updateUnionInfo：<input type="text" name="jsonStr">
        <br><br>
        <input type="submit" value="登录">
    </form>
    <br><br>
<%--<script src="test-js/login.js"></script>--%>
</body>
</html>

