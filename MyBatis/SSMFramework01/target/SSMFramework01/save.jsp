<%--
  Created by IntelliJ IDEA.
  User: xj
  Date: 2021-04-26
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>添加账户信息的表单</h1>
<form name="accountForm" action="${pageContext.request.contextPath}/account/save" method="post">
    账户名称：<input type="text" name="name"><br>
    账户金额：<input type="text" name="money"><br>
    <input type="submit" value="保存"><br>
</form>
</body>
</html>