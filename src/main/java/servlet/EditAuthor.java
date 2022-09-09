package servlet;

import manager.AuthorManager;
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

@WebServlet(urlPatterns = "/author/edit")
@MultipartConfig(
        fileSizeThreshold = 1024*1024*1,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*100
)
public class EditAuthor extends HttpServlet {

    AuthorManager authorManager = new AuthorManager();
    private static final String IMAGE_PATH = "C:\\Users\\user\\Downloads\\myLibrary-master\\projectImages\\";

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
        Part authorPicPart = req.getPart("authorPic");
        String fileName = null;
        if (authorPicPart.getSize() != 0 ){
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + authorPicPart.getSubmittedFileName();

            authorPicPart.write(IMAGE_PATH + fileName);
        }

        Author author = Author.builder()
                .id(Id)
                .name(name)
                .surname(surname)
                .email(email)
                .authorPic(fileName)
                .build();
        authorManager.edit(author);
        resp.sendRedirect("/authors");
    }
}
