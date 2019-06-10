package com.vng.authservice;

import com.vng.authservice.config.H2JpaConfig;
import com.vng.authservice.model.User;
import com.vng.authservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { H2JpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUser(){

        User user = new User("name","email","password","chatcode",true);
        userRepository.save(user);

        assertThat(userRepository.findByEmail("email").get().getEmail())
                .isEqualTo(user.getEmail());
    }
}
