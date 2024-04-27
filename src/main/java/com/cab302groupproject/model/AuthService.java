package com.cab302groupproject.model;

import java.util.Optional;
import java.util.Random;
import javafx.scene.control.TextInputDialog;

import static com.cab302groupproject.controller.SignUpController.displayErrorMessage;

public class AuthService {
    private static SqliteUserDAO userDAO = new SqliteUserDAO();

    public static User login(String email) {
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
}
