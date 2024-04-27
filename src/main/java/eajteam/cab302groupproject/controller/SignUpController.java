package eajteam.cab302groupproject.controller;

import eajteam.cab302groupproject.MainApplication;
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

    public boolean isValidEmail(String email) {
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

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
        if (isValidEmail(emailTextField.getText())) {
            // Add new user to database
            User user = new User(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText());
            try {
                userDAO.addUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Change scene to login-view
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
            stage.setScene(scene);
        } else { // Display error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Email Address");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onSwitchToLoginButtonClick() throws IOException {
        // Change scene to login-view
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        signUpButton.setDisable(!accepted);
    }
}
