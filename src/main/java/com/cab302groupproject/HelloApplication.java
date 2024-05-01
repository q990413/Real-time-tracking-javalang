package com.cab302groupproject;

import com.cab302groupproject.controller.NotificationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        VBox root = fxmlLoader.load();
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        // Load notification-view.fxml and set its controller
        FXMLLoader notificationLoader = new FXMLLoader(HelloApplication.class.getResource("notification-view.fxml"));
        VBox notificationRoot = notificationLoader.load();
        Scene notificationScene = new Scene(notificationRoot, 320, 240);

        // Set the stage for NotificationController
        NotificationController notificationController = notificationLoader.getController();
        notificationController.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}