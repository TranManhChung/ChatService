package com.vng.chatservice.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vng.chatservice.cache.Repository;
import com.vng.chatservice.model.ChatMessage;
import com.vng.chatservice.model.Message;
import com.vng.chatservice.model.Room;
import com.vng.chatservice.repositories.MessageRepository;
import com.vng.chatservice.repositories.RoomRepository;
import com.vng.chatservice.service.FileNameHash;
import com.vng.chatservice.service.FileStorage;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        System.out.println("handleBinaryMessage");

        if(wsSession.containsKey(session.getId())){
            System.out.println(wsSession);
            System.out.println(wsSession.get(session.getId()));
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject)jsonParser.parse(wsSession.get(session.getId()));
            wsSession.remove(session.getId());
            String type=jsonObject.get("type").toString();
            type=type.substring(1,type.length()-1);
            String roomId=jsonObject.get("roomId").toString();
            roomId=roomId.substring(1,roomId.length()-1);
            String fileName=jsonObject.get("content").toString();
            fileName=fileName.substring(1,fileName.length()-1);
            String sender=jsonObject.get("sender").toString();
            sender=sender.substring(1,sender.length()-1);
            String content=jsonObject.get("content").toString();
            content=content.substring(1,content.length()-1);
            System.out.println(jsonObject);
            System.out.println(type);
            if (type.equals("GET_FILE")){
                System.out.println("OK BABY");
                byte[] fileBytes = Files.readAllBytes(Paths.get("/home/cpu11290/ChatService/ChatMicroservice/chat-service/src/data/"+content));
                System.out.println(content.length());
                session.sendMessage(new BinaryMessage(fileBytes));
            }else {

                ChatMessage chatMessage=new ChatMessage();
                chatMessage.setRoomId(roomId);
                chatMessage.setSender(sender);
                chatMessage.setType(ChatMessage.MessageType.CHAT_LINK);
                chatMessage.setFileName(fileName);

                fileName=fileName+String.valueOf(System.currentTimeMillis());
                chatMessage.setContent(fileName);

                Optional<Room> room = Repository.room.findByIdRoom(roomId);
                byte[] b=new byte[message.getPayloadLength()];

                for (int i=0;i<message.getPayloadLength();i++){
                    b[i]= message.getPayload().array()[i];
                }

                if (FileStorage.saveFile(b,fileName)) System.out.println("save done");
                else System.out.println("failed to save");
                Blob blob = new SerialBlob(b );
                if(room.isPresent()){
                    Repository.messageRepository.save(new Message(fileName,jsonObject.get("sender").toString(),room.get(),fileName));
                }

                //session.sendMessage(new BinaryMessage(("http://localhost:8080/fileUpload/"+fileName).getBytes()));
                System.out.println(Repository.messagingTemplate);





                Repository.messagingTemplate.convertAndSend("/topic/" + roomId,chatMessage );

            }

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
