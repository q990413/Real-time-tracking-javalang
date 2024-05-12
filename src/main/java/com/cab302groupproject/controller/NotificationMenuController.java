package com.cab302groupproject.controller;

import com.cab302groupproject.TranquilifyApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static com.cab302groupproject.model.AuthService.displayErrorMessage;

public class NotificationMenuController {
    @FXML
    private TextArea notificationText;
    private Timeline timeline;
    @FXML
    private TextField secondsField;
    @FXML
    private TextField minutesField;
    @FXML
    private TextField hoursField;
    @FXML
    private Button notificationBackButton;

    private Stage stage;

    @FXML
    public void initialize() {
        notificationText.setText("Take a 10 minute break...");
    }

    @FXML
    protected void onSetNotificationButtonClick() {
        int delayInSeconds = calculateDelayInSeconds();
        if (delayInSeconds <= 0) {
            displayErrorMessage("Invalid Input", "Please fill in valid delay values.");
        } else {
            startPopupAfterDelay(delayInSeconds);
        }
    }

    @FXML
    protected void onNotificationBackButtonClick() throws IOException {
        Stage stage = (Stage) notificationBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("homepage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
    }

    private int calculateDelayInSeconds() {
        try {
            int seconds = secondsField.getText().isEmpty() ? 0 : Integer.parseInt(secondsField.getText());
            int minutes = minutesField.getText().isEmpty() ? 0 : Integer.parseInt(minutesField.getText());
            int hours = hoursField.getText().isEmpty() ? 0 : Integer.parseInt(hoursField.getText());

            return seconds + (minutes * 60) + (hours * 3600);
        } catch (NumberFormatException e) {
            return -1; // Indicates invalid input
        }
    }

    private void startPopupAfterDelay(int delayInSeconds) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(delayInSeconds), event -> {
            showPopup();
            timeline.stop(); // Stop the timeline after showing the popup
        }));
        timeline.setCycleCount(1); // Only run once
        timeline.play();
    }

    private void showPopup() {
        Label label = new Label(notificationText.getText());
        label.setWrapText(true); // Enable text wrapping
        label.setAlignment(Pos.CENTER); // Center align the text

        // Calculate preferred width based on the length of the message
        double prefWidth = Math.min(label.getText().length() * 10, 400); // Adjust as needed

        VBox popupRoot = new VBox();
        popupRoot.getChildren().add(label);
        popupRoot.setAlignment(Pos.CENTER); // Center align the VBox vertically
        popupRoot.setPadding(new Insets(10)); // Add padding for better appearance

        Scene scene = new Scene(popupRoot, prefWidth, Region.USE_COMPUTED_SIZE); // Set preferred width

        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.setTitle("Tranquilify Notification");
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(stage);

        popupStage.show();
    }
}