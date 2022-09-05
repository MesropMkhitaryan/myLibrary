package manager;

import db.DBConnectionProvider;
import model.Author;
import model.Book;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    AuthorManager authorManager = new  AuthorManager();
    public void add(Book book) {
        String sql = "insert into book(title,description,price,author_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setInt(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> getAll() {
        String sql = "select * from book";
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .price(resultSet.getInt(4))
                        .author(authorManager.getById(resultSet.getInt(5)))
                        .build();
                books.add(book);}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Book getBooksFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
            Book book = new Book();
            book.setId(resultSet.getInt(1));
            book.setTitle(resultSet.getString(2));
            book.setDescription(resultSet.getString(3));
            book.setPrice(resultSet.getInt(4));
            int eventId = resultSet.getInt(5);
            Author author = authorManager.getById(eventId);
            book.setAuthor(author);
            return book;


    }

    public Book getById(int id) {
        String sql = "select * from book where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getBooksFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void removeBook(int id){

        String sql = "delete from book where id ="+id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void edit(Book book) {
        String sql = "update book set title = ?,description = ?,price = ?,author_id = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setInt(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.setInt(5, book.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
