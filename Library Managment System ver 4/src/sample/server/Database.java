package sample.server;

import sample.Book;
import sample.Client;
import sample.Librarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Statement statement;
    private static Connection connection;

    public static void initializeDB() throws SQLException, ClassNotFoundException {
         Class.forName("com.mysql.cj.jdbc.Driver");
         ServerSide.updateTextArea("Database driver loaded\n");
         connection = DriverManager.getConnection("jdbc:mysql://localhost/library", "root", "Saints4ever");
         ServerSide.updateTextArea("Connected to database\n");
         statement = connection.createStatement();

    }

    public static Boolean signInDB(String username, String password) throws SQLException {

        String query = "SELECT *" +
                "FROM admins " +
                "WHERE username = '" + username + "';";

        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            String pass = resultSet.getString("password");
            if (pass.equals(password)) {
                connection.close();
                ServerSide.updateTextArea("Client is signed in\n");
                return true;
            }
        }
        return false;
    }

    public static Boolean addBookDB(Book book) throws SQLException {

        String query = "INSERT INTO book(book_title, author, publisher, genre, year)" +
                "VALUES('"+ book.getBookTitle() + "', '" +book.getAuthor()+ "'" +
                ",'"+ book.getPublisher()+ "','"+book.getGenre()+"','" + book.getYear()+"');";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.executeUpdate();
        connection.close();
        ServerSide.updateTextArea("Client added book\n" + book.toString());
        return true;

    }

    public static List<Book> viewBookDB() throws SQLException {

        String query = "SELECT book_title, author, publisher, genre, year FROM book;";

        ResultSet rs = statement.executeQuery(query);

        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            books.add(new Book(rs.getString("book_title"), rs.getString("author"), rs.getString("publisher"), rs.getString("genre"), rs.getString("year")));
        }
        connection.close();

        return books;

    }

    public static boolean signUpDB(String username, String password) throws SQLException {


        String query = "INSERT INTO admins(username, password)" +
                "VALUES('"+ username + "', '" +password +"');";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.executeUpdate();
        connection.close();
        ServerSide.updateTextArea("New client added to DB\n");
        return true;


    }

    public static Boolean issueBookDB(Client client) throws SQLException {

        String query1 = "select book_title from book where book_title = '" + client.getLoanedBook().getBookTitle() + "';";

        ResultSet rs1 = statement.executeQuery(query1);


        if (rs1.next()) {
            if (!client.getLoanedBook().getBookTitle().equals(rs1.getString("book_title"))) {
                ServerSide.updateTextArea("Failed to add client record");
                connection.close();
                return false;
            }
        }

        String query2 = "Select loaned_book from client where loaned_book = '"+client.getLoanedBook().getBookTitle()+"';";

        ResultSet rs2 = statement.executeQuery(query2);

        if (rs2.next()) {
            if (client.getLoanedBook().getBookTitle().equals(rs2.getString("loaned_book"))) {
                ServerSide.updateTextArea("Failed to add client record");
                connection.close();
                return false;
            }
        }

        String query3 = "insert into client(first_name, last_name, address, gender, due_date, loaned_book)" +
                "Values('"+client.getFirstName()+"','"+client.getLastName()+"','"+client.getAddress()+"','"+client.getGender()+"','"+client.getDueDate()+"','"+client.getLoanedBook().getBookTitle()+"');";

        PreparedStatement preparedStatement = connection.prepareStatement(query3);
        preparedStatement.executeUpdate();
        connection.close();
        ServerSide.updateTextArea("Client record added to DB\n", client.toString() +'\n');
        return true;

    }

    public static List<Client> viewLoanedBooksDB() throws SQLException {

        String query = "SELECT * FROM client;";

        ResultSet rs = statement.executeQuery(query);

        List<Client> clients = new ArrayList<>();

        int i = 0;


        while (rs.next()) {

            clients.add(new Client(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("gender")));
            clients.get(i).setDueDate(rs.getString("due_date"));
            clients.get(i).setLoanedBook(new Book(rs.getString("loaned_book")));
            clients.get(i).setId(rs.getString("client_id"));
            i++;
        }
        connection.close();

        ServerSide.updateTextArea("Client is requesting to view loaned books\n");

        return clients;
    }

    public static Boolean addLibrarianDB(Librarian librarian) throws SQLException {

        String query = "INSERT INTO librarian(librarian_id, first_name, last_name, email, address, gender)" +
                "VALUES("+ librarian.getId() + ", '" +librarian.getFirstName()+ "'" +
                ",'"+ librarian.getLastName()+ "','"+librarian.getEmail()+"','" + librarian.getAddress()+"','"+librarian.getGender()+"');";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        connection.close();
        ServerSide.updateTextArea("Librarian info added\n");
        return true;


    }

    public static List<Librarian> viewLibrarianDB() throws SQLException {

        String query = "SELECT * FROM librarian;";

        ResultSet rs = statement.executeQuery(query);

        List<Librarian> librarians = new ArrayList<>();

        while (rs.next()) {
            librarians.add(new Librarian(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("gender"), rs.getInt("librarian_id"), rs.getString("email")));
        }
        connection.close();
        ServerSide.updateTextArea("Client is requesting to view librarians information");

        return librarians;
    }

}

