package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientSide {

    private static Socket socket;
    private static final int PORT = 7590;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;


    public static void establishConnection() throws IOException {
        socket = new Socket("127.0.0.1", PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public static Boolean signInRequest(String username, String pass) throws IOException, ClassNotFoundException {
        out.writeObject("Sign in");
        out.writeObject(username);
        out.writeObject(pass);
        return (Boolean) in.readObject();

    }

    public static Boolean addBookRequest(Book book) throws IOException, ClassNotFoundException {
        out.writeObject("Add Book");
        out.writeObject(book);
        return (Boolean) in.readObject();

    }

    public static List<Book> viewBooks() throws IOException, ClassNotFoundException {
        out.writeObject("View books");
        return (ArrayList<Book>) in.readObject();
    }

    public static Boolean signUpRequest(String username, String pass) throws IOException, ClassNotFoundException {
        out.writeObject("Sign Up");
        out.writeObject(username);
        out.writeObject(pass);
        return (Boolean) in.readObject();

    }

    public static Boolean issueBookRequest(Client client) throws IOException, ClassNotFoundException {
        out.writeObject("Issue Book");
        out.writeObject(client);
        return (Boolean) in.readObject();
    }

    public static List<Client> viewLoanedBooksRequest() throws IOException, ClassNotFoundException {
        out.writeObject("View Loaned Books");
        return (ArrayList<Client>) in.readObject();
    }

    public static Boolean addLibrarianRequest(Librarian librarian) throws IOException, ClassNotFoundException {
        out.writeObject("Add Librarian");
        out.writeObject(librarian);
        return (Boolean) in.readObject();
    }

    public static List<Librarian> viewLibrariansRequest() throws IOException, ClassNotFoundException {
        out.writeObject("View Librarian");
        return (ArrayList<Librarian>) in.readObject();
    }

}