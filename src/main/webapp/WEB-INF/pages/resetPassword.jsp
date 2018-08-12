<%--
  Created by IntelliJ IDEA.
  User: q
  Date: 2018/8/11
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your New Password</title>
</head>
<body>
<%
  String newPassword=(String)session.getAttribute("newPassword");
  out.println("你的新密码为："+newPassword);
%>
</body>
</html>
