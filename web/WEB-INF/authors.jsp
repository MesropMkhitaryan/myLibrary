<%@ page import="model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.09.2022
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>
<%
    List<Author> authorList = (List<Author>) request.getAttribute("authors");

%>
<table border="1">
    <tr>
        <th>image</th>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>action</th>
    </tr>

    <% for (Author author : authorList) {%>
    <tr>
        <td>

            <%if (author.getAuthorPic() == null || author.getAuthorPic().length() == 0){%>
                 <img src="/img/img.png" width="100">
            <% } else {%>
            <img src="/getImage?authorPic=<%= author.getAuthorPic()%>" width="100"/>
            <% }%>
        </td>
        <td><%=author.getId()%>
        </td>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurname()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
        </td>
        <td><a href="/authors/remove?authorId=<%=author.getId()%>">remove</a>/
            <a href="/author/edit?authorId=<%=author.getId()%>">edit</a>
        </td>
    </tr>

    <% }%>

</table>


</body>
</html>
