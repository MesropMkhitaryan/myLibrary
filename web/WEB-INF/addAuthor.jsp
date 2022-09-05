<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.09.2022
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Author</title>
</head>
<body>

please add new author
<form action="/authors/add" method="post">
    <input type="text" name = "name" placeholder="Input name"><br>
    <input type="text" name = "surname" placeholder="Input surname"><br>
    <input type="email" name = "email" placeholder="Input email"><br>
    <input type="number" name="age" placeholder="input age">
    <br>
    <input type="submit" value="add"/>

</form>

</body>
</html>
