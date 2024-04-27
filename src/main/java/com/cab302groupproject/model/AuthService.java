package com.cab302groupproject.model;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import static com.cab302groupproject.controller.SignUpController.displayErrorMessage;
import static com.cab302groupproject.controller.SignUpController.isValidEmail;

public class AuthService {
    private static SqliteUserDAO userDAO = new SqliteUserDAO();

    public static User login(String email) {
        email = email.toLowerCase();

        // Generate code
        Random rand = new Random();
        String code = String.format("%04d", rand.nextInt(10000));
        // TODO: Make code expire after x minutes (code currently lasts forever as long as a new instance isn't started).

        // Email code if user exists in database
        User user = userDAO.getUser(email);
        if (user != null) {
            new SendEmail(email, "Tranquilify Login Code", "Your Tranquilify login code is: " + code);
        } else {
            return null;
        }

        // Create popup to enter code
        Integer enteredCode = -1;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Login Code");
        dialog.setHeaderText("Please enter your login code");
        // Get response value
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            enteredCode = Integer.parseInt(result.get());
        }

        if (enteredCode.equals(Integer.parseInt(code))) {
            return user;
        } else {
            displayErrorMessage("Incorrect Login Code", "The login code you entered was incorrect.");
        }
        return null;
    }

    public static boolean signUp(String firstName, String lastName, String email) throws IOException {
        email = email.toLowerCase();

        // Validate user input
        if (userDAO.getUser(email) != null) {
            displayErrorMessage("Account Already Exists", "The email address you entered is already associated" +
                    " with an existing account.");
            return false;
        } else if (isValidEmail(email) && !firstName.isEmpty() && !lastName.isEmpty()) {
            // Add new user to database
            User user = new User(firstName, lastName, email);
            userDAO.addUser(user);
            return true;
        } else if (!isValidEmail(email) && (firstName.isEmpty() || lastName.isEmpty())) {
            displayErrorMessage("Form Incomplete", "Please enter your first name, last name, and a valid email " +
                    "address.");
            return false;
        } else if (firstName.isEmpty() || lastName.isEmpty()) {
            displayErrorMessage("Name Field Empty", "Please enter your first name and last name.");
            return false;
        } else { // Email address must be only incorrect/empty field
            displayErrorMessage("Invalid Email Address", "Please enter a valid email address.");
            return false;
        }
    }
}
