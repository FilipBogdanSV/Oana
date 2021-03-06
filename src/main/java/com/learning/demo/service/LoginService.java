package com.learning.demo.service;

import com.learning.database.DatabaseConnectionProvider;
import com.learning.database.DatabaseOperations;
import com.learning.demo.model.LoginRequestEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.learning.database.DatabaseOperations.getDatabaseService;

@Component
public class LoginService {

    public boolean checkIfUserExists(LoginRequestEntity loginRequestEntity) {
        DatabaseOperations databaseService = getDatabaseService();
        String username = null;
        String password = null;
        ResultSet resultSet;
        try {
            resultSet = databaseService.queryUsers("Users");
            resultSet.next();
            username = resultSet.getNString("username");
            password = resultSet.getNString("password");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginRequestEntity.getPassword().equals(password) && loginRequestEntity.getUsername().equals(username);
    }


 }
