package com.learning.demo.Controller;

import com.learning.demo.Controller.utils.ServerUtils;
import com.learning.demo.model.LoginRequestEntity;
import com.learning.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.learning.demo.Controller.utils.ServerUtils.*;

@RestController
public class DemoAppController {

    @Autowired
    LoginService loginService;

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
}
