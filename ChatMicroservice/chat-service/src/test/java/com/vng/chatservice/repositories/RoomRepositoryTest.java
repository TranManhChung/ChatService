package com.vng.chatservice.repositories;

import com.vng.chatservice.model.Room;
import com.vng.chatservice.repositories.RoomRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoomRepository roomRepository;

    private static Room room;

    @Before
    public void init(){
        room = new Room("id-1");
        entityManager.persistAndFlush(new Room("id-2"));//room 1
        entityManager.persistAndFlush(room);//room 2 <-------
        entityManager.persistAndFlush(new Room("id-3"));//room 3
    }

    @After
    public void reset(){
        roomRepository.deleteAll();
        entityManager.clear();
    }

    @Test
    public void whenFindByIdRoom_thenReturnARoom(){
        assertThat(roomRepository.findByIdRoom(room.getIdRoom()).isPresent()).isEqualTo(true);
    }

    @Test
    public void whenFindByInvalidIdRoom_thenReturnNull(){
        assertThat(roomRepository.findByIdRoom("random-room-id").isPresent()).isEqualTo(false);
    }
}
