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
	<form action="/test" method="POST" enctype="multipart/form-data">
		<label>Profile Picture</label>
        <input type="file" name="profilePicture" accept="image/jpeg,image/png,image/gif">
        <br>
        <input type="submit" value="submit">
    </form>
</body>
</html>

