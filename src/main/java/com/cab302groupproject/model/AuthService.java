package com.cab302groupproject.model;

import java.util.Random;

import static com.cab302groupproject.controller.SignUpController.displayErrorMessage;

public class AuthService {
    private static SqliteUserDAO userDAO = new SqliteUserDAO();

    public static User login(String email) {
        User user = userDAO.getUser(email);
        if (user == null) {
            displayErrorMessage("Account Does Not Exist", "The email address you entered is not in our database.");
            return null;
        }
        // Generate code
        Random rand = new Random();
        String code = String.format("%04d", rand.nextInt(10000));

        // Email code


        // Check entered code


        return user;
    }
}
