import java.sql.*;

public class BookDao {
    private final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";
    private Connection connection;

    public BookDao() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    //dodaj
    public void save(Book book) throws SQLException {
        final String insert = "insert into books (title, author, year,isbn) values (?,?,?,?)";
        PreparedStatement saveStatement = connection.prepareStatement(insert);

        saveStatement.setString(1, book.getTitle());
        saveStatement.setString(2, book.getAuthor());
        saveStatement.setInt(3, book.getYear());
        saveStatement.setInt(4, book.getIsbn());
        saveStatement.executeUpdate();
    }

    //wyszukaj
    public Book read(int id) throws SQLException {
        final String query = "select * from books where id =?";
        PreparedStatement readStatement = connection.prepareStatement(query);

        readStatement.setInt(1, id);
        ResultSet resultSet = readStatement.executeQuery();
        Book result = null;
        if (resultSet.next()) {
            result = new Book();
            result.setTitle(resultSet.getString("title"));
            result.setAuthor(resultSet.getString("author"));
            result.setYear(resultSet.getInt("year"));
            result.setIsbn(resultSet.getInt("isbn"));
        }
        return result;
    }

    //usuń
    public void delete (int id) throws SQLException {
        final String remove = "delete from books where id=?";
        PreparedStatement deleteStatement = connection.prepareStatement(remove);

        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();


    }



}
