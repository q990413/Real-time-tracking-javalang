package eajteam.cab302groupproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import eajteam.cab302groupproject.controller.NotificationController;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        VBox root = fxmlLoader.load();
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        // Load Notification.fxml and set its controller
        FXMLLoader notificationLoader = new FXMLLoader(MainApplication.class.getResource("Notification.fxml"));
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