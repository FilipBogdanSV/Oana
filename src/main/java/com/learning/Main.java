package com.learning;

import com.learning.database.DatabaseConnectionProvider;
import com.learning.database.DatabaseOperations;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = new DatabaseConnectionProvider()
                .getConnectionToDatabase("jdbc:mysql://85.204.241.125:3306", "sinfl-filip-bogdan", "98rbmn");
        DatabaseOperations databaseOperations = new DatabaseOperations(connection, "sinfl-filip-bogdan");
        databaseOperations.addNewRegistration("oana_sabou", "12345678", "oana_sabou", "Users");


    }
}
