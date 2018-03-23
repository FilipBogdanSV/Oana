package com.learning.demo.Controller;

import com.learning.database.DatabaseOperations;
import com.learning.demo.Controller.utils.ServerUtils;
import com.learning.demo.model.LoginRequestEntity;
import com.learning.demo.model.Message;
import com.learning.demo.model.RegisterRequestEntity;
import com.learning.demo.service.LoginService;
import com.learning.demo.service.MessageService;
import com.learning.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.learning.demo.Controller.utils.ServerUtils.*;

@RestController
public class DemoAppController {

    @Autowired
    LoginService loginService;
    @Autowired
    RegisterService registerService;
    @Autowired
    MessageService messageService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/JSON"})
    public ResponseEntity<Object> loginRequest(@RequestBody LoginRequestEntity loginRequestEntity) {
        String state = UNAUTHORIZED;
        if (loginService.checkIfUserExists(loginRequestEntity)) {
            state = AUTHORIZED;
        }
        if (state.equals(AUTHORIZED)) {
            String token = ServerUtils.encodeToBase64(loginRequestEntity.getUsername() + TWO_POINTS + loginRequestEntity.getPassword());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {"application/JSON"})
    public ResponseEntity<Object> registerRequest(@RequestBody RegisterRequestEntity registerRequestEntity) {
        boolean success = registerService.registerNewUser(registerRequestEntity);
        if (success) {
            return ResponseEntity.ok("Succesfully registered");
        } else {
            return ResponseEntity.badRequest().body("");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST, consumes = {"application/JSON"})
    public ResponseEntity<Object> sendMessage(@RequestBody Message message,
                                              @RequestHeader("Authorization") String authorization) {
        messageService.saveMessageToDb(authorization, message);

        return ResponseEntity.accepted().body("");
    }

    @CrossOrigin
    @RequestMapping(value = "/getMessages")
    public List<Message> returnMyMessages(@RequestHeader("Authorization") String authorization) {
        return messageService.checkForMessages(authorization);
    }

    //testing purpose
    @CrossOrigin
    @RequestMapping(value = "/getAllUsers")
    public List<String> getAllUsers() throws SQLException {
        List<String> userNames = new ArrayList<>();
        DatabaseOperations databaseOperations = DatabaseOperations.getDatabaseService();
        ResultSet resultSet = databaseOperations.queryUsers("Users");
        while (resultSet.next()) {
            userNames.add(resultSet.getString("username"));
        }
        return userNames;
    }
}
