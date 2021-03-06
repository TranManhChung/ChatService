package com.vng.chatservice.support;

import com.vng.chatservice.global.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null) {
            logger.info("User Disconnected : " + username);
            for( int i=0 ; i<Global.listOnlineUser.size() ; i++ ){
                if(Global.listOnlineUser.get(i).getUsername().equals(username)){
                    Global.listOnlineUser.remove(i);
                    break;
                }
            }
            messagingTemplate.convertAndSend("/topic/onlineList", Global.listOnlineUser);

            //ChatMessage chatMessage = new ChatMessage();
            //chatMessage.setType(ChatMessage.MessageType.LEAVE);
            //chatMessage.setSender(username);
            //messagingTemplate.convertAndSend("/topic/publicChatRoom", chatMessage);
        }
    }

}