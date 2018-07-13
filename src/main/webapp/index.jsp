<<<<<<< Updated upstream
﻿<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@include file="common/base.jsp" %>--%>
=======
<%@ page import="scau.zxck.entity.sys.AdminInfo" %>
<%@ page import="scau.zxck.dao.sys.AdminInfoDao" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="WEB-INF/pages/common/base.jsp" %>
>>>>>>> Stashed changes

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>豐隆</title>
</head>

<body>
<<<<<<< Updated upstream
	<form action="/login" method="POST">
		用户名:<br>
		<input type="text" name="userName">
		<br>
		密码:<br>
		<input type="text" name="password">
		<br><br>
		<input type="submit" value="登录">
    </form>
=======
  <form action="/login2" method="post">
      goods_list:<input type="text" name="goods_list">
      <br>
      goods_num:<input type="text" name="goods_num" >
      <br><br>
      <input type="submit" value="提交">
  </form>
<script src="${jsPath}/lib/jquery.min.js"></script>
<script src="${jsPath}/lib/bootstrap.min.js"></script>
>>>>>>> Stashed changes
</body>
</html>

