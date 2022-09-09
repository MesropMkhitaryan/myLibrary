package servlet;

import manager.AuthorManager;
import model.Author;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/authors/add")
@MultipartConfig(
        fileSizeThreshold = 1024*1024*1,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*100
)
public class AddAuthorServlet extends HttpServlet {
    AuthorManager authorManager = new AuthorManager();

    private static final String IMAGE_PATH = "C:\\Users\\user\\Downloads\\myLibrary-master\\projectImages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null){
            req.getRequestDispatcher("/WEB-INF/addAuthor.jsp").forward(req,resp);
       }else {
            resp.sendRedirect("/");
       }


    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        Part authorPicPart = req.getPart("authorPic");
        String fileName = null;
        if (authorPicPart.getSize() != 0 ){
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + authorPicPart.getSubmittedFileName();

            authorPicPart.write(IMAGE_PATH + fileName);
        }



        Author author = Author.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .authorPic(fileName)
                .build();
        authorManager.add(author);
        resp.sendRedirect("/authors");
    }
}