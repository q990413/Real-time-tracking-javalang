package org.example.cab302groupproject.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.cab302groupproject.Model;

import java.io.IOException;

public class NotificationController {
    @FXML
    private TextArea notificationWords;
    private Stage stage;
    private Timeline timeline;
    @FXML
    private TextField secondsField;
    @FXML
    private TextField minutesField;
    @FXML
    private TextField hoursField;
    @FXML
    private Button NotificationBackButton;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        notificationWords.setText("WRITE HERE FOR POPUP WINDOW");
    }

    @FXML
    protected void onPopupButtonClick() {
        int delayInSeconds = calculateDelayInSeconds();
        if (delayInSeconds < 0) {
            showErrorPopup("Please fill in valid delay values.");
        } else if (delayInSeconds == 0) {
            showErrorPopup("Delay should be greater than 0.");
        } else {
            startPopupAfterDelay(delayInSeconds);
        }
    }

    @FXML
    protected void onNotificationBackButton() throws IOException {
        Stage stage = (Stage) NotificationBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Model.class.getResource("view.fxml"));
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
        Label label = new Label(notificationWords.getText());
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
        popupStage.setTitle("Popup");
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(stage);

        popupStage.show();
    }

    private void showErrorPopup(String message) {
        Stage popupStage = new Stage();
        VBox popupRoot = new VBox();
        Label label = new Label(message);
        label.setAlignment(Pos.CENTER); // Center align the label
        popupRoot.getChildren().add(label);
        popupRoot.setAlignment(Pos.CENTER); // Center align the VBox
        Scene scene = new Scene(popupRoot, 300, 100);

        popupStage.setTitle("Error");
        popupStage.setScene(scene);

        // Set modal behavior
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(stage);

        popupStage.show();
    }
}