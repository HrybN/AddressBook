package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        primaryStage.setTitle("Адресна книга");

        Scene scene = new Scene(root, 600, 600);

        primaryStage.setScene(scene);

        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);
        primaryStage.show();
    }
}
