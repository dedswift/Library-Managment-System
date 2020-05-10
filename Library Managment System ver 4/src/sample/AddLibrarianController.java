package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddLibrarianController {

    @FXML
    private TextField id;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private TextField gender;



    @FXML
    public void submit(ActionEvent event) {

        try {

            if (id.getText().equals("")|| firstName.getText().equals("")|| lastName.getText().equals("")|| gender.getText().equals("") || email.getText().equals("")) {
                AlertMessage.alert(Alert.AlertType.INFORMATION, "Missing information");
                return;
            }

            if( !(gender.getText().equals("f")||gender.getText().equals("F")||gender.getText().equals("m")||gender.getText().equals("M"))) {
                AlertMessage.alert(Alert.AlertType.INFORMATION, "Please enter gender as m/M for male and f/F for female");
                return;
            }


            if (ClientSide.addLibrarianRequest(new Librarian(firstName.getText(), lastName.getText(), address.getText(), gender.getText(), Integer.parseInt(id.getText().trim()), email.getText()))) {

                AlertMessage.alert(Alert.AlertType.INFORMATION, null, null, "Librarian added");

                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(root, 600, 400));

                stage.show();

            }
            else {
                AlertMessage.alert(Alert.AlertType.CONFIRMATION,"ID must be unique");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException ex) {
            AlertMessage.alert(Alert.AlertType.CONFIRMATION, "ID should be in numbers");
        }
    }


        @FXML
    public void back(ActionEvent event) {
        new Controller().back(event);
    }

}
