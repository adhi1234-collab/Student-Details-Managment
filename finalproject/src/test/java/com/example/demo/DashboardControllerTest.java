package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.demo.controller.DashboardController;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.lang.reflect.Field;

public class DashboardControllerTest {

    private DashboardController controller;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() throws Exception {
        controller = new DashboardController();
        userRepository = mock(UserRepository.class);

        // Inject mock into controller
        Field repoField = DashboardController.class.getDeclaredField("userRepository");
        repoField.setAccessible(true);
        repoField.set(controller, userRepository);
    }

    @Test
    public void testLoginSuccess() {
        // Arrange
        String username = "testuser";
        String password = "password";

        User dummyUser = new User();
        dummyUser.setUsername(username);
        dummyUser.setPassword(password);

        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class); // ✅ Required now

        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(dummyUser);

        // Act
        String result = controller.login(username, password, model, session); // ✅ Updated method call

        // Assert
        assertThat(result).isEqualTo("redirect:/dashboard");
    }
}

