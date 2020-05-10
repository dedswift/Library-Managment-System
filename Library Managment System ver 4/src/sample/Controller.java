package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private TextField bookTitle;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private TextField genre;

    @FXML
    private TextField year;


    @FXML
    public void signIn(ActionEvent event) {
        try {
            ClientSide.establishConnection();
            if (ClientSide.signInRequest(username.getText(), password.getText())) {

                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(root, 600, 400));

                stage.show();

            } else {
                if (username.getText().equals("")||password.getText().equals(""))
                    AlertMessage.alert(Alert.AlertType.INFORMATION,null, null,"Please enter your sign in information");
                else
                    AlertMessage.alert(Alert.AlertType.INFORMATION, "Invalid Username or password", null, "Username or password are incorrect");

            }
        }catch (IOException | ClassNotFoundException ex) {
            AlertMessage.alert(Alert.AlertType.WARNING, "Warning", null, "Server is offline");
        }
    }

    @FXML
    public void signOut(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("SignInSignUp.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root,560,310));

        stage.show();
    }

    @FXML
    public void signUp(ActionEvent event) {

        try {
            String user = username.getText();
            String pass = password.getText();



            ClientSide.establishConnection();
            if (user.equals("")|| pass.equals("")) {
                AlertMessage.alert(Alert.AlertType.CONFIRMATION, null, null, "Please enter your sign up info");
                return;
            }
            if (ClientSide.signUpRequest(username.getText(), password.getText())) {
                AlertMessage.alert(Alert.AlertType.CONFIRMATION, null, null, "Account added");
            }
            else {

                AlertMessage.alert(Alert.AlertType.INFORMATION, null, null, "Username already taken");

            }

        } catch (IOException | ClassNotFoundException e) {
            AlertMessage.alert(Alert.AlertType.WARNING, "Warning", null, "Server is offline");
        }



    }

    @FXML
    public void addBook(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("AddBook.fxml"));

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root,600,400));

        stage.show();

    }

    @FXML
    public void bookAddBtn(ActionEvent event) {
        try {
            if (ClientSide.addBookRequest(new Book(bookTitle.getText(),author.getText(),publisher.getText(),genre.getText(),year.getText()))) {

                AlertMessage.alert(Alert.AlertType.CONFIRMATION, null, null, "Book Added");

                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(root, 600, 400));

                stage.show();
            } else {
                if (bookTitle.getText().equals("")||author.getText().equals(""))
                    AlertMessage.alert(Alert.AlertType.INFORMATION,null, null,"please enter book title");
                else
                    AlertMessage.alert(Alert.AlertType.INFORMATION, "Invalid Username or password", null, "Username or password are incorrect");

            }
        }catch (IOException ex) {
            AlertMessage.alert(Alert.AlertType.WARNING, "Warning", null, ex.getMessage());
        }
        catch (NumberFormatException e) {
            AlertMessage.alert(Alert.AlertType.WARNING,null, null, "Please enter a number in the year field");
        }
        catch (NullPointerException nullPointerException) {
            AlertMessage.alert(Alert.AlertType.WARNING, null, null, nullPointerException.getMessage());
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }


    }
    @FXML
    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR,null,null, e.getMessage());
        }
    }


    @FXML
    public void loanBook(ActionEvent event) {

        try {
            ClientSide.establishConnection();
            Parent root = FXMLLoader.load(getClass().getResource("IssueBookPage.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR,null,null, e.getMessage());
        }
    }



    @FXML
    public void viewBook(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ViewBooks.fxml"));

            new BooksTableController().loadTable();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 605, 400));

            stage.show();
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR,null,null, e.getMessage());
        }

    }

    @FXML
    public void viewLoanedBooks(ActionEvent event) {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("ViewLoanedBooksPage.fxml"));

            new ViewLoanedBooksController().loadTable();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 605, 400));
            stage.show();


        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, null, null, "IO Exception");
        }


    }

    @FXML
    public void addLibrarian(ActionEvent event) {

        try {
            ClientSide.establishConnection();
            Parent root = FXMLLoader.load(getClass().getResource("AddLibrarianPage.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR,null,null, e.getMessage());
        }
    }

    @FXML
    public void viewLibrarian(ActionEvent event) {

        try {
            ClientSide.establishConnection();

            Parent root = FXMLLoader.load(getClass().getResource("ViewLibrarian.fxml"));

            new ViewLibrarianController().loadTable();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 605, 400));

            stage.show();
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR,null,null, e.getMessage());
        }

    }

}
