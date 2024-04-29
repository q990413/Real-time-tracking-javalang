package com.cab302groupproject.controller;

import com.cab302groupproject.TranquilifyApplication;
import com.cab302groupproject.model.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.function.UnaryOperator;

import static com.cab302groupproject.model.AuthService.signUp;

/**
 * A controller class for the sign-up-view FXML file.
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
    private SqliteUserDAO userDAO;

    /**
     * Constructor that retrieves a
     */
    public SignUpController() {
        userDAO = new SqliteUserDAO();
    }

    // ---START HELPER METHODS---
    //TODO: Move to Utility class

    /**
     * Validates the given email address against a regex pattern that captures most valid email addresses (source:
     * https://emailregex.com/).
     * @param email The email address to be validated.
     * @return True if given email is a valid email address, or false otherwise.
     */
    public static boolean isValidEmail(String email) {
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x" +
                "08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:" +
                "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?" +
                "[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0" +
                "c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    /**
     * Displays a JavaFX popup window alert with the given title as the window title, and the given content as the message.
     * @param title The title of the popup window.
     * @param content The text content of the popup window.
     */
    public static void displayErrorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // ---END HELPER METHODS---

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

    @FXML
    public void initialize() {
        firstNameTextField.setTextFormatter(new TextFormatter<>(nameValidationFormatter));
        lastNameTextField.setTextFormatter(new TextFormatter<>(nameValidationFormatter));
    }

    @FXML
    protected void onCancelButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();

        boolean signUpSuccess = signUp(firstName, lastName, email);
        if (signUpSuccess) {
            // Change scene to login-view
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), TranquilifyApplication.WIDTH, TranquilifyApplication.HEIGHT);
            stage.setScene(scene);
        }
    }

    @FXML
    protected void onSwitchToLoginButtonClick() throws IOException {
        // Change scene to login-view
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), TranquilifyApplication.WIDTH, TranquilifyApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        signUpButton.setDisable(!accepted);
    }
}
