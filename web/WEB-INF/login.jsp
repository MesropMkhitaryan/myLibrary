<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.09.2022
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>

<%
    String msg = (String) request.getAttribute("msg");

%>

<%  if (msg != null) { %>

<p style="color: red"><%= msg%> </p>
<%}%>

<form action="/login" method="post">
<input type="email" name="email" placeholder="input email">
<input type="password" name="password" placeholder="input password">

<input type="submit" value="login">
</form>
</body>
</html>
