package com.example.socketchat.controller;

import com.example.socketchat.model.MessageChat;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

//    @MessageMapping("/chat.addUser")
//    @SendTo("/broker/toChat")
//    public Message addUser(@Payload Message chatMessage,
//                           SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getUsername());
//        return chatMessage;
//    }

    @MessageMapping("/toApp")
    @SendTo("/broker/toChat")
    public MessageChat sendMessage(MessageChat messageChat) {
        return messageChat;
    }
}
