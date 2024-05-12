package com.cab302groupproject.model;

/**
 * A class to model users of the application with a first name, last name, and email address.
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Constructs a new user object given a first name, last name, and email address.
     *
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The email address of the user
     */
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}