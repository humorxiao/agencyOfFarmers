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
<%
    request.setAttribute("yhx","666777888999");
    request.setAttribute("abc","def");
    request.setAttribute("123","456");
    String name="Hachiko";
    Cookie c=new Cookie("username",name);
    c.setMaxAge(24*3600);
    response.addCookie(c);
    session.setAttribute("ILYJL","520");
    session.setAttribute("YJLIL","530");
%>
	<form action="/login" method="POST">
		测试：<input type="text" name="jsonStr">
		<%--&lt;%&ndash;<br><br>&ndash;%&gt;--%>
		<input type="submit" value="test">
    </form>
</body>
</html>

