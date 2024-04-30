package com.cab302groupproject.controller;

import com.cab302groupproject.model.AuthService;
import com.cab302groupproject.TranquilifyApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A controller class for the login-view.fxml file.
 */
public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField emailTextField;

    /**
     * Attempts to sign the user in with the entered info.
     * @throws IOException
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        String email = emailTextField.getText();

        if (AuthService.login(email) != null) {
            // Change scene to main view
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            stage.setMaximized(true);
            stage.setScene(scene);
        }
    }

    /**
     * Changes the view to the sign-up-view.fxml file when the Switch to Sign Up button is clicked.
     * @throws IOException
     */
    @FXML
    protected void onSwitchToSignUpButtonClick() throws IOException {
        // Change scene to sign up view
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("sign-up-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), TranquilifyApplication.WIDTH, TranquilifyApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Closes the application when the Cancel button is clicked.
     */
    @FXML
    protected void onCancelButtonClick() {
        System.exit(0);
    }
}