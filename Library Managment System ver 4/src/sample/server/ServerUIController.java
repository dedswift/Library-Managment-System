package sample.server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerUIController implements Initializable {


    public TextArea getTextArea() {
        return textArea;
    }

    @FXML
    private static TextArea textArea;


    public static void updateTextArea(String message) {
        Platform.runLater(() ->{
            textArea.appendText(message);
        });


    }

    public static void updateTextArea(String message, String type) {
        Platform.runLater(() ->{
            textArea.appendText(message + type);
        });

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText("hello");
//        Platform.runLater(() ->{
//            textArea.appendText("Sever online");
//        });

    }
}
