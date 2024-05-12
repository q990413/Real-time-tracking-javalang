package com.cab302groupproject.controller;

import com.cab302groupproject.TranquilifyApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {
    @FXML
    private Button notificationMenuButton;

    @FXML
    protected void onNotificationMenuButtonClick() throws IOException {
        Stage stage = (Stage) notificationMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("notification-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), TranquilifyApplication.WIDTH, TranquilifyApplication.HEIGHT);
        stage.setScene(scene);
    }
}
