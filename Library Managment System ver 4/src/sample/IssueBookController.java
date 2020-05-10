package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class IssueBookController {


    @FXML
    private TextField firstNm;

    @FXML
    private TextField lastNm;

    @FXML
    private TextField address;

    @FXML
    private TextField gender;

    @FXML
    private TextField book;

    @FXML
    private TextField dueD;




    @FXML
    public void issueBook(ActionEvent event) {

        if( !(gender.getText().equals("") || gender.getText().equals("f")||gender.getText().equals("F")||gender.getText().equals("m")||gender.getText().equals("M"))) {
            AlertMessage.alert(Alert.AlertType.INFORMATION, null, null, "Please enter gender as m/M for male and f/F for female");
            return;
        }

        if (book.getText().equals("") || dueD.getText().equals("")) {
            AlertMessage.alert(Alert.AlertType.INFORMATION, null , null, "Due date/book empty field");
            return;
        }

        Client client = new Client(firstNm.getText(), lastNm.getText(), address.getText(), gender.getText());
        client.setLoanedBook(new Book(book.getText()));
        client.setDueDate(dueD.getText());
        try {
            if (ClientSide.issueBookRequest(client))
                AlertMessage.alert(Alert.AlertType.INFORMATION, null, null,"Book issued");
            else
                AlertMessage.alert(Alert.AlertType.INFORMATION, null, null,"Book can't be issued");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void back(ActionEvent event) {
        new Controller().back(event);
    }

}
