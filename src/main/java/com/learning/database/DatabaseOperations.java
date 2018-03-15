package com.learning.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {
    private Statement statement;

    public DatabaseOperations(Connection databaseConnection, String databaseName) {
        try {
            statement = databaseConnection.createStatement();
            statement.executeQuery("use " + databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDatabase(String databaseName) throws SQLException {
        statement.executeQuery("CREATE DATABASE " + databaseName);
    }

    public void createTable() throws SQLException {
        statement.executeQuery("CREATE TABLE Users (\n" +
                "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,\n" +
                "username VARCHAR(30) NOT NULL,\n" +
                "password VARCHAR(30) NOT NULL,\n" +
                "email VARCHAR(50),\n" +
                "reg_date TIMESTAMP\n" +
                ")");
    }

    public ResultSet queryUsers(String tableName) throws SQLException {
        return statement.executeQuery("select * from " + tableName);
    }

    public void addNewRegistration(String username, String password, String email, String tableName) throws SQLException {
        statement.executeQuery(String.format("INSERT INTO " + tableName + " values (%s, %s, %s)", username, password, email));
    }
}