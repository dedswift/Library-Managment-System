package sample.server;

import javafx.application.Platform;
import sample.Book;
import sample.Client;
import sample.Librarian;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ClientTaskHandler implements Runnable {

    private ObjectInputStream inObj;
    private ObjectOutputStream outObj;


    public ClientTaskHandler(Socket clientSocket) throws IOException, ClassNotFoundException {
        inObj = new ObjectInputStream(clientSocket.getInputStream());
        outObj = new ObjectOutputStream(clientSocket.getOutputStream());
    }



    @Override
    public void run() {
        try {

            while (true) {
                String type = (String) inObj.readObject();
                ServerSide.updateTextArea("Client is requesting to " + type+'\n');
                if (type.equals("Sign in")) {
                    String username = (String) inObj.readObject();
                    String password = (String) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.signInDB(username, password));
                }
                else if (type.equals("Add Book")) {
                    Book book = (Book) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.addBookDB(book));

                }
                else if (type.equals("View books")){
                    Database.initializeDB();
                    outObj.writeObject(Database.viewBookDB());
                }
                else if(type.equals("Sign Up")) {
                    String username = (String) inObj.readObject();
                    String password = (String) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.signUpDB(username, password));
                }
                else if (type.equals("Issue Book")) {
                    Client client = (Client) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.issueBookDB(client));
                }
                else if(type.equals("View Loaned Books")) {
                    Database.initializeDB();
                    outObj.writeObject(Database.viewLoanedBooksDB());
                }
                else if(type.equals("Add Librarian")) {
                    Librarian librarian = (Librarian) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.addLibrarianDB(librarian));
                }
                else if(type.equals("View Librarian")) {
                    Database.initializeDB();
                    outObj.writeObject(Database.viewLibrarianDB());
                }

            }

        }catch (SQLException ex) {
            try {
                outObj.writeObject(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(IOException ex) {
            System.out.println("bish");
        }
        catch(ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }

    public void signIn() {

    }
}
