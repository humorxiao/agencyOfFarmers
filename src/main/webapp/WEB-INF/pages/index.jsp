<%@ page import="scau.zxck.utils.CodeUtil" %>
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
<%--<form action="/check" method="POST">--%>
<%--自己的邮箱：<input type="text" name="from">--%>
<%--<br>--%>
<%--pj的邮箱：<input type="text" name="email">--%>
<%--<br>--%>
<%--瞎jb乱写就行了反正也没用：<input type="text" name="code">--%>
<%--<br>--%>
<%--pj授权码：<input type="text" name="accreditCode">--%>
<%--<br>--%>
<%--你想发几封：<input type="text" name="count">--%>
<%--<br>--%>
<%--<input type="submit" value="提交">--%>
<%--<br><br>--%>
<%--</form>--%>
<%--<form action="/testEmail" method="POST">--%>
<%--自己的邮箱：<input type="text" name="from">--%>
<%--<br>--%>
<%--pj的邮箱：<input type="text" name="to">--%>
<%--<br>--%>
<%--pj授权码：<input type="text" name="accreditCode">--%>
<%--<br>--%>
<%--<input type="submit" value="提交">--%>
<%--<br><br>--%>
<%--</form>--%>

<%--<form action="/headImgUpload" method="POST" enctype="multipart/form-data">--%>
<%--<label>Profile Picture</label>--%>
<%--<input type="file" name="file" accept="image/jpeg,image/png,image/gif">--%>
<%--<br>--%>
<%--<input type="submit" value="submit">--%>
<form action="/uploadPictures" method="POST" enctype="multipart/form-data">
  <label>Profile Picture</label>
  <input type="file" name="image" accept="image/jpeg,image/png,image/gif">
  <br>
  <input type="submit" value="submit">
</form>
<%--</form>--%>
<%--<form action="/test" method="post">--%>
<%--username:<input type="text" name="name">--%>
<%--<br>--%>
<%--password:<input type="text" name="password">--%>
<%--<br><br>--%>
<%--<input type="submit" value="submit">--%>
<%--</form>--%>
<%--<form action="/test" method="post">--%>
  <%--email:<input type="text" name="email">--%>
  <%--<br>--%>
  <%--accreditCode:<input type="text" name="accreditCode">--%>
  <%--<br>--%>
  <%--from:<input type="text" name="from">--%>
  <%--<br>--%>
  <%--count:<input type="text" name="count">--%>
  <%--<br>--%>
  <%--<input type="submit" value="submit">--%>
<%--</form>--%>
</body>
</html>

