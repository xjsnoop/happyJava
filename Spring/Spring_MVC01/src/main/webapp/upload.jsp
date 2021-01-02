<%--
  Created by IntelliJ IDEA.
  User: xj
  Date: 2021-01-02
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/quick19" method="post" enctype="multipart/form-data">
        名称<input type="text" name="username"><br/>
        文件<input type="file" name="uploadFile"><br/>
        文件<input type="file" name="uploadFile"><br/>
        文件<input type="file" name="uploadFile"><br/>
        文件<input type="file" name="uploadFile"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
