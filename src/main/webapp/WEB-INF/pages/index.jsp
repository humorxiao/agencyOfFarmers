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
<<<<<<< HEAD
	<%--<form action="login.jsp" method="POST">--%>
		<%--<input type="submit" value="提交">--%>
    <%--</form>--%>
    <form action="/login" method="POST">
        测试：<input type="test" name="jsonStr">
        <input type="submit" value="提交">
=======
	<form action="/test" method="POST" enctype="multipart/form-data">
		<label>Profile Picture</label>
        <input type="file" name="profilePicture" accept="image/jpeg,image/png,image/gif">
        <br>
        <input type="submit" value="submit">
>>>>>>> 637d6278c2878fdcbcbad3748dac6c9d02a33f63
    </form>
</body>
</html>

