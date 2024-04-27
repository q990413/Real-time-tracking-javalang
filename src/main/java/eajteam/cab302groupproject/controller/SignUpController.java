package eajteam.cab302groupproject.controller;

import eajteam.cab302groupproject.TranquilifyApplication;
import eajteam.cab302groupproject.model.SqliteUserDAO;
import eajteam.cab302groupproject.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.function.UnaryOperator;

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

    public SignUpController() {
        userDAO = new SqliteUserDAO();
    }

    // ---START HELPER METHODS---
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

    public static boolean isValidEmail(String email) {
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public static void displayErrorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // ---END HELPER METHODS---

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
        String email = emailTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        // Validate user input
        if (isValidEmail(email) && !firstName.isEmpty() && !lastName.isEmpty()) {
            // Add new user to database
            User user = new User(firstName, lastName, email);
            userDAO.addUser(user);

            // Change scene to login-view
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), TranquilifyApplication.WIDTH, TranquilifyApplication.HEIGHT);
            stage.setScene(scene);
        } else if (!isValidEmail(email) && (firstName.isEmpty() || lastName.isEmpty())) {
            displayErrorMessage("Form Incomplete", "Please enter your first name, last name, and a valid email " +
                    "address.");
        } else if (firstName.isEmpty() || lastName.isEmpty()) {
            displayErrorMessage("Name Field Empty", "Please enter your first name and last name.");
        } else { // Email address must be only incorrect/empty field
            displayErrorMessage("Invalid Email Address", "Please enter a valid email address.");
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
