package sample;

import javafx.beans.property.SimpleStringProperty;
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

public class ViewLibrarianController implements Initializable {


    @FXML
    private TableView<Librarian> librarianTable;

    @FXML
    private TableColumn<Librarian, String> idCol;

    @FXML
    private TableColumn<Librarian, String> firstNameCol;

    @FXML
    private TableColumn<Librarian, String> lastNameCol;

    @FXML
    private TableColumn<Librarian, String> genderCol;

    @FXML
    private TableColumn<Librarian, String> addressCol;

    @FXML
    private TableColumn<Librarian, String> emailCol;


    public List<Librarian> loadTable() {

        try {

            List<Librarian> librarians = ClientSide.viewLibrariansRequest();  //client side.view loaned books request

            ObservableList<Librarian> data = FXCollections.observableArrayList();

            for (int i = 0; i < librarians.size() ; i++) {
                if (librarians.get(i) != null) {
                    data.add(new Librarian(librarians.get(i).getFirstName(), librarians.get(i).getLastName(), librarians.get(i).getAddress(), librarians.get(i).getGender(),librarians.get(i).getId(), librarians.get(i).getEmail() ));
                }
            }

            return librarians;

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

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        librarianTable.getItems().setAll(loadTable());
    }
}
