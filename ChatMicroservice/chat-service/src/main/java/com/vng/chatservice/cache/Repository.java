package com.vng.chatservice.cache;

import com.vng.chatservice.repositories.MessageRepository;
import com.vng.chatservice.repositories.RoomRepository;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class Repository {
    public static RoomRepository room;
    public static MessageRepository messageRepository;
    public static SimpMessageSendingOperations messagingTemplate;
}
