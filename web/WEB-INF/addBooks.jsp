<%@ page import="java.util.List" %>
<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.09.2022
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Books</title>
</head>
<body>
<%

    List<Author> authors = (List<Author>) request.getAttribute("authors");


%>

Please add books

<form action="/books/add" method="post">
    <input type="text" name = "title" placeholder="Input title"><br>
    <input type="text" name = "description" placeholder="Input description"><br>
    <input type="number" name = "price" placeholder="Input price"><br>
<select name="authorId">
        <% for (Author author : authors) { %>
        <option value="<%=author.getId()%>"><%= author.getName()%>
            <%= author.getSurname()%></option>
       <% } %>
</select>
    <br>
    <input type="submit" value="Register"/>

</form>

</body>
</html>
