package com.cab302groupproject.model;

/**
 * Interface for the User Data Access Object that handles
 * the CRUD operations for the User class with the database.
 */
public interface IUserDAO {
    /**
     * Adds a new user to the database.
     * @param user The user to add.
     */
    public void addUser(User user);
    /**
     * Updates an existing user in the database.
     * @param user The user to update.
     */
    public void updateUser(User user);
    /**
     * Deletes a user from the database.
     * @param user The user to delete.
     */
    public void deleteUser(User user);
    /**
     * Retrieves a user from the database.
     * @param email The email address of the user to retrieve.
     * @return The user with the given email, or null if not found.
     */
    public User getUser(String email);
}
