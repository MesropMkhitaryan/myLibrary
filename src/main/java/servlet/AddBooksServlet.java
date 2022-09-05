package servlet;

import lombok.SneakyThrows;
import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books/add")
public class AddBooksServlet extends HttpServlet {
    AuthorManager authorManager = new AuthorManager();
    BookManager bookManager = new BookManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> all = authorManager.getAll();
        req.setAttribute("authors",all);
        List<Book> bookList = bookManager.getAll();
        req.setAttribute("books",bookList);
        req.getRequestDispatcher("/WEB-INF/addBooks.jsp").forward(req,resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Book book = Book.builder().title(title).description(description)
                .price(price).author(authorManager.getById(authorId)).build();
        bookManager.add(book);
        resp.sendRedirect("/books");
    }
}
