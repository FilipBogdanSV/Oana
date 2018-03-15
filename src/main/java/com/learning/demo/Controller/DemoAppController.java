package com.learning.demo.Controller;

import com.learning.demo.model.LoginRequestEntity;
import com.learning.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoAppController {

    @Autowired
    LoginService loginService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/JSON"})
    public String loginRequest(@RequestBody LoginRequestEntity requestObject) {
        return null;

    }
}
