<%@ page import="model.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.09.2022
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<%
    List<Book> booksList = (List<Book>) request.getAttribute("book");


%>

<table border="1">
    <tr>
        <th>image</th>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author name and surname</th>
        <th>action</th>
    </tr>

    <% for (Book book : booksList) { %>
    <tr>
        <td>
            <% if (book.getBookPic() == null || book.getBookPic().length() == 0){ %>
            <img src="/img/defaultBookPic.png" width="100" alt="">
            <% } else {%>
            <img src="/getImagebook?booksPic=<%= book.getBookPic()%>" alt="" width="100">
            <% }%>
        </td>
        <td><%=book.getId()%></td>
        <td><%=book.getTitle()%></td>
        <td><%=book.getDescription()%></td>
        <td><%=book.getPrice()%></td>
        <td>
            <% if (book.getAuthor() != null){%>
            <%=book.getAuthor().getName()%> <%=book.getAuthor().getSurname()%>
            <%} else {%>
             <span style="color: red">there is no author</span>
            <%}%>
        </td>
        <td><a href="/books/remove?bookId=<%=book.getId()%>">remove</a> |
            <a href="/books/edit?bookId=<%=book.getId()%>">edit</a>
        

        </td>
    </tr>

    <% }%>

</table>


</body>
</html>
