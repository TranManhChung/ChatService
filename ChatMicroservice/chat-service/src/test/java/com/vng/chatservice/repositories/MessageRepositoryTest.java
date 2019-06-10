package com.vng.chatservice.repositories;

import com.vng.chatservice.model.Message;
import com.vng.chatservice.model.Room;
import com.vng.chatservice.repositories.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageRepository messageRepository;

    private static Room room;
    private static List<Message> messageList;

    public void init(boolean findListMess){
        room = new Room("room_id_1");
        entityManager.persistAndFlush(room);

        entityManager.persistAndFlush(new Message("mess","a@gmail.com",room,"no-file-name"));
        entityManager.persistAndFlush(new Message("mess","b@gmail.com",room,"no-file-name"));
        entityManager.persistAndFlush(new Message("mess","a@gmail.com",room,"no-file-name"));
        entityManager.persistAndFlush(new Message("mess","b@gmail.com",room,"no-file-name"));

        if(findListMess == true){
            messageList = messageRepository.findAllByRoom(room).get();
        }
    }


    @Test
    public void whenFindAllMessByRoomId_thenReturnListMess(){

        init(true);
        int totalMess = messageList.size();
        assertThat(totalMess).isEqualTo(4);
    }

    @Test
    public void whenFindAllMessByInvalidRoomId_thenReturnNull(){

        init(false);
        room = new Room();
        entityManager.persistAndFlush(room);
        assertThat(messageRepository.findAllByRoom(room).isPresent()).isFalse();
    }

    @Test
    public void whenFindAEmailInMessByEmail_thenReturnTrue(){

        init(true);
        assertThat(messageList.get(0).getEmail()).isEqualTo("a@gmail.com");
        assertThat(messageList.get(3).getEmail()).isEqualTo("b@gmail.com");
    }

    @Test
    public void whenFindAEmailInMessByInvalidEmail_thenReturnFalse(){

        init(true);
        assertThat(messageList.get(0).getEmail()).isNotEqualTo("aaaaa@gmail.com");
        assertThat(messageList.get(3).getEmail()).isNotEqualTo("bbbbb@gmail.com");
    }

}
