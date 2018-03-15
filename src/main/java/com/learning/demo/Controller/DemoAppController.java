package com.learning.demo.Controller;

import com.learning.demo.model.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoAppController {

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/JSON"})
    public String loginRequest(@RequestBody RequestEntity requestObject) {
        System.out.println(requestObject.getPassword());
        System.out.println(requestObject.getUsername());
        return null;
    }
}
