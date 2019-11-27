package com.example.socketchat.config;

import com.example.socketchat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
       sendEventMsg(Message.Type.JOIN, event.getMessage());
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        sendEventMsg(Message.Type.LEAVE, event.getMessage());
    }

    private void sendEventMsg(Message.Type type, org.springframework.messaging.Message<byte[]> message) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            Message msg = new Message();
            msg.setUsername(username);
            msg.setType(type);
            msg.setBody("");
            messagingTemplate.convertAndSend("/chat/toApp", message);
        }
    }
}
