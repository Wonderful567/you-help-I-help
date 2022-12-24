<%--
  Created by IntelliJ IDEA.
  User: 14234
  Date: 2022/12/15
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>

</head>
<body>

  <form action="select" method="get">
    用户名<input type="text" name="username"/><br>
    密码<input type="password" name="password"/><br>
    <input type="submit" value="登录"/>
      <input type="button" onclick="window.location.href='register.jsp';" value="注册新用户">
  </form>
  <input type="button" onclick="window.location.href='admin.jsp';" value="管理员登录">

</body>
</html>
