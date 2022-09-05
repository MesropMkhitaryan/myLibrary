package servlet;

import manager.AuthorManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/author/edit")
public class EditAuthor extends HttpServlet {

    AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Author author = authorManager.getById(authorId);
        req.setAttribute("author",author);
        req.setAttribute("authors",authorManager.getAll());
        req.getRequestDispatcher("/WEB-INF/editAuthor.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("authorId"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        Author author = Author.builder()
                .id(Id)
                .name(name)
                .surname(surname)
                .email(email)
                .build();
        authorManager.edit(author);
        resp.sendRedirect("/authors");
    }
}
