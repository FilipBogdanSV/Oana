package com.learning.demo.service;

import com.learning.database.DatabaseOperations;
import com.learning.demo.model.RegisterRequestEntity;
import org.springframework.stereotype.Component;

@Component
public class RegisterService {

    public boolean registerNewUser(RegisterRequestEntity registerRequestEntity) {
        DatabaseOperations databaseOperations = DatabaseOperations.getDatabaseService();
        try {
            databaseOperations.addNewRegistration(registerRequestEntity.getUsername(), registerRequestEntity.getPassword(), registerRequestEntity.getEmail(), "Users");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
