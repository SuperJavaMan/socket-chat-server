package com.example.socketchat.config;

import com.example.socketchat.model.MessageChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        Principal principal = event.getUser();
        if (principal == null) {
            sendEventMsg(MessageChat.Type.JOIN, event.getMessage());
        } else {
            throw new RuntimeException("Not authorized");
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        sendEventMsg(MessageChat.Type.LEAVE, event.getMessage());
    }

    private void sendEventMsg(MessageChat.Type type, org.springframework.messaging.Message<byte[]> message) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        String username = null;
        if (headerAccessor.getSessionAttributes() != null)
            username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            MessageChat msg = new MessageChat();
            msg.setUsername(username);
            msg.setType(type);
            msg.setBody("");
            messagingTemplate.convertAndSend("/chat/toApp", message);
        }
    }
}
