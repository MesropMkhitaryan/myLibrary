<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.09.2022
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Library</title>
</head>
<%

    User user = (User) session.getAttribute("user");


%>
<body>

<% if (user != null) {%>
<a href="/authors/add">add author</a>
<a href="/books/add">add books</a>
<a href="/logout">Logout</a>

<%} else {%>
<a href="/users/add">Register</a>
<a href="/login">Login</a>
<%}%>
<a href="/authors">Show all authors</a>
<a href="/books">Show all books</a>


</body>
</html>
