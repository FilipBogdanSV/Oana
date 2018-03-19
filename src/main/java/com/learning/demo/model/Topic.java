package com.learning.demo.model;

import com.learning.demo.Controller.utils.ServerUtils;

public class Topic {
    private int id;
    private String message;

    public Topic(String to, String from, String message) {
        id = ServerUtils.generateAsciiFromStrings(to + from);
        this.message = message;
    }

}
