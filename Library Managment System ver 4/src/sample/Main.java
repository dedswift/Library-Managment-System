package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SignInSignUp.fxml"));
        primaryStage.setTitle("Library Management System CS244");
        primaryStage.setScene(new Scene(root, 560, 310));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
