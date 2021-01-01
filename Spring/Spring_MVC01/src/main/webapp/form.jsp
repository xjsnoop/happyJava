<%--
  Created by IntelliJ IDEA.
  User: xj
  Date: 2020-12-30
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/quick12" method="post">
<%--        表明是第几个user对象的username--%>
        <input type="text" name="userList[0].username">
        <input type="text" name="userList[0].age">
        <input type="text" name="userList[1].username">
        <input type="text" name="userList[1].age">
        <input type="submit" value="提交">
    </form>
</body>
</html>
