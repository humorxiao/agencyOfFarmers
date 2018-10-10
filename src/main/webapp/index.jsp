<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/common/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>豐隆</title>
</head>
<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script type="text/javascript">
  function aaa() {
    alert(returnCitySN.cip);
  }
</script>
<body>

<form action="/loginTest" method="post">
  <input type="text" name="name">
  <br>
  <input type="text" name="email">
  <br>
  <input type="submit" value="提交">
  <br>
</form>
<input type="button" value="ip地址" onclick="aaa()">
</body>
</html>
