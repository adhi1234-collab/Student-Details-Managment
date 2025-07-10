package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");

        userRepository.save(user);

        User found = userRepository.findByUsernameAndPassword("testuser", "testpass");

        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("testuser");
    }
}
