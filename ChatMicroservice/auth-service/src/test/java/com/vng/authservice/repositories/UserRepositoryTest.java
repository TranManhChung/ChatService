package com.vng.authservice.repositories;

import com.vng.authservice.model.User;
import com.vng.authservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUser(){

        User user = new User("name","email","password","chatcode",true);
        entityManager.persist(user);
        entityManager.flush();

        assertThat(userRepository.findByEmail("email").get().getEmail())
                .isEqualTo(user.getEmail());
    }

    @Test
    public void whenInvalidEmail_thenReturnNull() {
        Optional<User> user = userRepository.findByEmail("doesNotExist");
        assertThat(user.isPresent()).isEqualTo(false);
    }
}
