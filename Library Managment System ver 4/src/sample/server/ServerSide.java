package sample.server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerSide extends Application {

    private static final int PORT = 7590;
    private static TextArea textArea;
    private ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void start(Stage primaryStage) throws Exception {

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefHeight(399);
        textArea.setPrefWidth(598);

        ScrollPane root = new ScrollPane(textArea);
        primaryStage.setTitle("Library Server");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e->{
                Platform.exit();
                System.exit(0);
        });

        executor.execute(() -> {

            try {
                ServerSocket socket = new ServerSocket(PORT);

                while (true) {
                    Socket clientSocket = socket.accept();
                    executor.execute(new ClientTaskHandler(clientSocket));
                }

            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("!!!!");
            }

        });

    }

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


}
