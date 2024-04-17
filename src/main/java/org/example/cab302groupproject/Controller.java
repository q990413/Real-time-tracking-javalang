package org.example.cab302groupproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private Button NotificationTestButton;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void testingNotificationButton() throws IOException {
        Stage stage = (Stage) NotificationTestButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Model.class.getResource("Notification.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
    }
}