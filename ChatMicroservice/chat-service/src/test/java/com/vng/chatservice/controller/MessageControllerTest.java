package com.vng.chatservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vng.chatservice.ChatServiceApplication;
import com.vng.chatservice.model.Message;
import com.vng.chatservice.model.Room;
import com.vng.chatservice.repositories.MessageRepository;
import com.vng.chatservice.repositories.RoomRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ChatServiceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepository;

    private static Room room;

    public void init() {
        room = new Room("room_id_1");
        roomRepository.saveAndFlush(room);

        messageRepository.saveAndFlush(new Message("mess 1", "a@gmail.com", room, "no-file-name"));
        messageRepository.saveAndFlush(new Message("mess 2", "b@gmail.com", room, "no-file-name"));
        messageRepository.saveAndFlush(new Message("mess 3", "a@gmail.com", room, "no-file-name"));
        messageRepository.saveAndFlush(new Message("mess 4", "b@gmail.com", room, "no-file-name"));

    }

    @After
    public void cleanUp() {
        messageRepository.deleteAll();
        roomRepository.deleteAll();
    }

    @Test
    public void whenGetListMessByRoomId_thenSuccess() throws Exception {

        init();

        mvc.perform(get("/messages/" + room.getIdRoom()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$.status", is("success")));
    }

    @Test
    public void whenGetListMessByRoomId_thenError() throws Exception {

        init();

        mvc.perform(get("/messages/" + room.getIdRoom()+"random").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("error")));
    }

}
