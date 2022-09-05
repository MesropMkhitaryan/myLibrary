<%@ page import="model.Book" %>
<%@ page import="model.Author" %><%--
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
<%
    Author author = (Author) request.getAttribute("author");

%>


please add new author
<form action="/author/edit" method="post">
    <input type="hidden" name="authorId" value="<%=author.getId()%>"><br>
    <input type="text" name = "name" value="<%=author.getName()%>"><br>
    <input type="text" name = "surname" value="<%=author.getSurname()%>"><br>
    <input type="email" name = "email" value="<%=author.getEmail()%>"><br>
    <input type="number" name="age" value="<%=author.getAge()%>">
    <br>
    <input type="submit" value="Update"/>

</form>

</body>
</html>
