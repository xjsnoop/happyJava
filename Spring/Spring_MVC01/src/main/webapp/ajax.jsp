<%--
  Created by IntelliJ IDEA.
  User: xj
  Date: 2020-12-30
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script>
        var userList = new Array();
        userList.push({username:"zhangshan",age:18});
        userList.push({username:"lisi",age:55});
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/quick13",
            data:JSON.stringify(userList),
            contentType:"application/json;charset=utf-8"
        })
    </script>
</head>
<body>

</body>
</html>
