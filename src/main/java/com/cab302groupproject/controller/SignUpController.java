package com.cab302groupproject.controller;

import com.cab302groupproject.TranquilifyApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.function.UnaryOperator;

import static com.cab302groupproject.model.AuthService.signUp;

/**
 * A controller class for the sign-up-view.fxml file.
 */
public class SignUpController {
    @FXML
    private CheckBox agreeCheckBox;
    @FXML
    private Button signUpButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;

    /**
     * Only allows characters that are common for english names to be entered in a text field.
     */
    UnaryOperator<TextFormatter.Change> nameValidationFormatter = change -> {
        if (change.getText().matches("[a-zA-Z' -]+")) {
            return change; // If change is typical char used in english name
        } else {
            change.setText(""); // Make no change
            change.setRange(    // Don't remove any selected text
                    change.getRangeStart(),
                    change.getRangeEnd()
            );
            return change;
        }
    };

    /**
     * Initialisation function that applies the nameValidationFormatter function to both name text fields so that invalid
     * characters cannot be entered.
     */
    @FXML
    public void initialize() {
        firstNameTextField.setTextFormatter(new TextFormatter<>(nameValidationFormatter));
        lastNameTextField.setTextFormatter(new TextFormatter<>(nameValidationFormatter));
    }

    /**
     * Closes the application when the Cancel button is clicked.
     */
    @FXML
    protected void onCancelButtonClick() {
        System.exit(0);
    }

    /**
     * Attempts to add a new user to the database with the entered info when the Sign Up button is clicked.
     * @throws IOException
     */
    @FXML
    protected void onSignUpButtonClick() throws IOException {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();

        if (signUp(firstName, lastName, email)) {
            // Change scene to login-view
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), TranquilifyApplication.WIDTH, TranquilifyApplication.HEIGHT);
            stage.setScene(scene);
        }
    }

    /**
     * Changes the view to the login-view.fxml file when the Switch to Login button is clicked.
     * @throws IOException
     */
    @FXML
    protected void onSwitchToLoginButtonClick() throws IOException {
        // Change scene to login-view
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), TranquilifyApplication.WIDTH, TranquilifyApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Enables the Sign Up button when the Agree Checkbox is ticked, and disables it otherwise.
     */
    @FXML
    protected void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        signUpButton.setDisable(!accepted);
    }
}
