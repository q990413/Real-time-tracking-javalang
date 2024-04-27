package eajteam.cab302groupproject.controller;

import eajteam.cab302groupproject.MainApplication;
import eajteam.cab302groupproject.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static eajteam.cab302groupproject.controller.SignUpController.displayErrorMessage;
import static eajteam.cab302groupproject.controller.SignUpController.isValidEmail;
import static eajteam.cab302groupproject.model.AuthService.login;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField emailTextField;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        String email = emailTextField.getText();

        // Validate user input
        if (isValidEmail(email)) {
            // Authenticate user
            login(email);

            // Change scene to main view if authenticated
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
            stage.setScene(scene);
        } else {
            displayErrorMessage("Invalid Email Address", "Please enter a valid email address.");
        }
    }

    @FXML
    protected void onSwitchToSignUpButtonClick() throws IOException {
        // Change scene to sign up view
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("sign-up-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onCancelButtonClick() {
        System.exit(0);
    }
}