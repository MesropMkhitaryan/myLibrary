package servlet;

import manager.AuthorManager;
import manager.BookManager;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet(urlPatterns = "/books/edit")
@MultipartConfig(
        fileSizeThreshold = 1024*1024*1,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*100
)
public class EditBook extends HttpServlet {
    private final BookManager bookManager = new BookManager();
    private final AuthorManager authorManager = new AuthorManager();
    private static final String IMAGE_PATH = "C:\\Users\\user\\Downloads\\myLibrary-master\\projectImagesBook\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        Book book = bookManager.getById(bookId);
        req.setAttribute("book", book);
        req.setAttribute("authors", authorManager.getAll());
        req.getRequestDispatcher("/WEB-INF/editBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("bookId"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Part books = req.getPart("booksPic");
        String bookName = null;
        if (books.getSize() != 0) {
            long nanoTime = System.nanoTime();
            bookName = nanoTime + "_" + books.getSubmittedFileName();

            books.write(IMAGE_PATH + bookName);
        }

        Book book = Book.builder()
                .id(id)
                .title(title)
                .description(description)
                .price(price)
                .bookPic(bookName)
                .author(authorManager.getById(authorId))
                .build();
        bookManager.edit(book);
        resp.sendRedirect("/books");
    }
}
