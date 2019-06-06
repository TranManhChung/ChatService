package com.vng.chatservice.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vng.chatservice.cache.Repository;
import com.vng.chatservice.model.Message;
import com.vng.chatservice.model.Room;
import com.vng.chatservice.repositories.MessageRepository;
import com.vng.chatservice.repositories.RoomRepository;
import javassist.bytecode.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import javax.sql.rowset.serial.SerialBlob;
import javax.websocket.OnMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@Component
public class PlainWebSocket extends BinaryWebSocketHandler {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepository;


    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("afterConnectionEstablished");
    }
    static HashMap<String,String> wsSession=new HashMap<>();
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {


        if(wsSession.containsKey(session.getId())){
            System.out.println(wsSession);
            System.out.println(wsSession.get(session.getId()));
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject)jsonParser.parse(wsSession.get(session.getId()));
            wsSession.remove(session.getId());


            String roomId=jsonObject.get("roomId").toString();
            roomId=roomId.substring(1,roomId.length()-1);
            System.out.println(roomId);
            Optional<Room> room = Repository.room.findByIdRoom(roomId);
            byte[] b=new byte[message.getPayloadLength()];

            for (int i=0;i<message.getPayloadLength();i++){
                b[i]= message.getPayload().array()[i];
            }
            Blob blob = new SerialBlob(b );

            if(room.isPresent()){
                Repository.messageRepository.save(new Message("",jsonObject.get("sender").toString(),room.get(),blob));
            }

            session.sendMessage(message);


            Repository.messagingTemplate.convertAndSend("/topic/" + roomId, message);

        }else {
            String re="";
            for (int i=0;i<message.getPayloadLength();i++){
                re=re+ (char)message.getPayload().array()[i];
            }



            wsSession.put(session.getId(),re);
        }


//        Optional<Room> room = roomRepository.findByIdRoom(chatMessage.getRoomId());
//        if(room.isPresent()){
//            messageRepository.save(new Message(chatMessage.getContent(),chatMessage.getSender(),room.get()));
//        }
//        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoomId(), chatMessage);


        System.out.println("handleBinaryMessage");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("afterConnectionClosed");

    }
}
