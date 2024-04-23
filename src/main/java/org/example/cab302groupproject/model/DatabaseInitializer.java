package org.example.cab302groupproject.model;
import java.sql.*;

public class DatabaseInitializer {

    private static final String URL = "jdbc:sqlite:userinfo"; //

    public static void main(String[] args) {
        createNewTable();
        //insert sample data
        insertUser("Alice", "alice123", "alice@example.com");
        insertUser("Bob", "bob456", "bob@example.com");
    }

    public static void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " username text NOT NULL UNIQUE,\n"
                + " password text NOT NULL,\n"
                + " email text\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table 'users' created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertUser(String username, String password, String email) {
        // SQL statement for inserting a new user
        String sql = "INSERT INTO users(username, password, email) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ((PreparedStatement) pstmt).setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User " + username + " inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


