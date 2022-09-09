<%@ page import="java.util.List" %>
<%@ page import="model.Author" %>
<%@ page import="model.Book" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.09.2022
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Books</title>
</head>
<body>
<%
    Book book =(Book) request.getAttribute("book");
    List<Author> authors = (List<Author>) request.getAttribute("authors");


%>

Please update book

<form action="/books/edit" method="post"  enctype="multipart/form-data">
    <input type="hidden" name="bookId" value="<%=book.getId()%>"><br>
    <input type="text" name = "title" value="<%=book.getTitle()%>"><br>
    <input type="text" name = "description" value="<%=book.getDescription()%>"><br>
    <input type="number" name = "price" value="<%=book.getPrice()%>"><br>
<select name="authorId">
        <% for (Author author : authors) {
        if (author.equals(book.getAuthor())){
        %>
    <option selected value="<%=author.getId()%>"><%= author.getName()%>
            <%= author.getSurname()%></option>
       <% }else {%>
    <option value="<%=author.getId()%>"><%= author.getName()%>
       <% }} %>
</select>
    <br>
    <input type="file" name="booksPic"/>
    <br>
    <input type="submit" value="Update"/>

</form>

</body>
</html>
