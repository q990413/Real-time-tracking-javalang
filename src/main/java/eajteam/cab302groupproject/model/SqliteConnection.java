package eajteam.cab302groupproject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class to create a connection to an SQLite database.
 */
public class SqliteConnection {
    private static Connection instance = null;

    /**
     * Establishes a connection to the users.db database, or prints an error to the console otherwise
     */
    private SqliteConnection() {
        String url = "jdbc:sqlite:users.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}