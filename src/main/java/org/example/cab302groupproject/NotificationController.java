package org.example.cab302groupproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;

public class NotificationController {
    @FXML
    private TextArea notificationWords;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        notificationWords.setText("WRITE HERE FOR POPUP WINDOW");
    }

    @FXML
    protected void onPopupButtonClick() {
        Stage popupStage = new Stage();
        VBox popupRoot = new VBox();
        Label label = new Label(notificationWords.getText());
        popupRoot.getChildren().add(label);
        Scene scene = new Scene(popupRoot, 200, 100);

        popupStage.setTitle("Popup");
        popupStage.setScene(scene);

        // Set modal behavior
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(stage);

        popupStage.showAndWait();
    }
}