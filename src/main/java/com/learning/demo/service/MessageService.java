package com.learning.demo.service;

import com.learning.demo.model.Message;
import com.learning.demo.model.MessageRequestEntity;
import com.learning.demo.model.Topic;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.learning.demo.Controller.utils.ServerUtils.decodeFromBase64;

@Component
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public void saveMessageToDb(String authorization, Message message) {
        message.setSender(StringUtils.substringBefore(authorization, ":"));
        messageRepository.save(message);
    }

    public List<Message> checkForMessages(String authorization) {
        List<Message> unreadMessages = new ArrayList<>();
        messageRepository.findAll().forEach(message -> {
            if (message.getReceiver().equals(StringUtils.substringBefore(authorization, ":"))) {
                if (!message.getConsumed()) {
                    unreadMessages.add(message);
                    message.setConsumed(true);
                    messageRepository.save(message);
                }
            }
        });
        return unreadMessages.size() > 0 ? unreadMessages : null;
    }
}
