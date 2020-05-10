package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BooksTableController implements Initializable {

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bookTitleCol;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> publisherCol ;

    @FXML
    private TableColumn<Book, String> genreCol ;

    @FXML
    private TableColumn<Book, String> yearCol ;


    public List<Book> loadTable() {

        try {

            List<Book> books = ClientSide.viewBooks();

            ObservableList<Book> data = FXCollections.observableArrayList();

            for (int i = 0; i < books.size() ; i++) {
                if (books.get(i) != null) {
                    data.add(new Book(books.get(i).getBookTitle(), books.get(i).getAuthor(), books.get(i).getPublisher(), books.get(i).getGenre(), books.get(i).getYear()));
                }
            }

            return books;

        } catch (IOException | ClassNotFoundException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, null, null, e.getMessage());

        }
        return null;
    }


    @FXML
    public void back(ActionEvent event) {
        new Controller().back(event);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bookTitleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        bookTable.getItems().setAll(loadTable());
    }
}
