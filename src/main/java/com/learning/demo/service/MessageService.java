package com.learning.demo.service;

import com.learning.demo.model.MessageRequestEntity;
import com.learning.demo.model.Topic;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.learning.demo.Controller.utils.ServerUtils.decodeFromBase64;
@Component
public class MessageService {

    public void messageNeedsToBeSent(String authorization, MessageRequestEntity messageRequestEntity) {
        String from = StringUtils.substringBefore(decodeFromBase64(authorization), ":");

    }
}
