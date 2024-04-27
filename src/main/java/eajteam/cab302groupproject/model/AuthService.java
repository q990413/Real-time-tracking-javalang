package eajteam.cab302groupproject.model;

import static eajteam.cab302groupproject.controller.SignUpController.displayErrorMessage;

public class AuthService {
    private static SqliteUserDAO userDAO = new SqliteUserDAO();

    public static User login(String email) {
        User user = userDAO.getUser(email);
        if (user == null) {
            displayErrorMessage("Account Does Not Exist", "The email address you entered is not in our database.");
        }
        // Generate code

        // Email code

        // Check entered code

        return user;
    }
}
