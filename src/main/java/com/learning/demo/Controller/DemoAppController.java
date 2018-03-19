package com.learning.demo.Controller;

import com.learning.demo.Controller.utils.ServerUtils;
import com.learning.demo.model.LoginRequestEntity;
import com.learning.demo.model.Message;
import com.learning.demo.model.MessageRequestEntity;
import com.learning.demo.model.RegisterRequestEntity;
import com.learning.demo.service.LoginService;
import com.learning.demo.service.MessageRepository;
import com.learning.demo.service.MessageService;
import com.learning.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {"application/JSON"})
    public ResponseEntity<Object> registerRequest(@RequestBody RegisterRequestEntity registerRequestEntity) {
        boolean success = registerService.registerNewUser(registerRequestEntity);
        if (success) {
            return ResponseEntity.ok("Succesfully registered");
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST, consumes = {"application/JSON"})
    public ResponseEntity<Object> sendMessage(@RequestBody MessageRequestEntity messageRequestEntity,
                                              @RequestHeader("Authorization") String authorization) {
        messageService.messageNeedsToBeSent(authorization, messageRequestEntity);

        return ResponseEntity.accepted().body(null);
    }

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/test")
    public String test() {
        return messageRepository.findOneByToName("Otto").toString();
    }
}
