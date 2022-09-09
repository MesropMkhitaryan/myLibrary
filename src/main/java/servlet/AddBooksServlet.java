package servlet;

import lombok.SneakyThrows;
import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024*1,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*100
)
public class AddBooksServlet extends HttpServlet {
    AuthorManager authorManager = new AuthorManager();
    BookManager bookManager = new BookManager();
    private static final String IMAGE_PATH = "C:\\Users\\user\\Downloads\\myLibrary-master\\projectImagesBook\\";

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

        Part books = req.getPart("booksPic");
        String bookName = null;
        if (books.getSize() != 0) {
            long nanoTime = System.nanoTime();
            bookName = nanoTime + "_" + books.getSubmittedFileName();

            books.write(IMAGE_PATH + bookName);
        }


        Book book = Book.builder()
                .title(title)
                .description(description)
                .price(price)
                .bookPic(bookName)
                .author(authorManager.getById(authorId))
                .build();
        bookManager.add(book);
        resp.sendRedirect("/books");
    }
}
