package com.cab302groupproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.cab302groupproject.HelloApplication;

import java.io.IOException;

public class HelloController {
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("notification-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 440);
        stage.setScene(scene);
    }
}