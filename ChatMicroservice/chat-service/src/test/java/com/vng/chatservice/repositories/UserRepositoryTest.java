package com.vng.chatservice.repositories;

import com.vng.chatservice.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private static User user;

    @Before
    public void init(){
        user = new User("", "email@gmail.com", "");

        entityManager.persistAndFlush(user);//persist user to db
    }

    @After
    public void reset(){
        userRepository.deleteAll();
        entityManager.clear();
    }

    @Test
    public void whenFindByEmail_thenReturnAUser(){
        Assert.assertNotNull("True", userRepository.findByEmail(user.getEmail()));
    }

    @Test
    public void whenFindByEmail_thenReturnNull(){
        String badEmail = "xxx@gmail.com";

        Assert.assertNull("True", userRepository.findByEmail(badEmail));
    }
}
