package ru.yaroglek.naujava.practice3.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import ru.yaroglek.naujava.practice3.app.repository.UserRepository;
import ru.yaroglek.naujava.practice3.domain.User;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan("ru.yaroglek.naujava.practice3.app")
class UserServiceTest {
    private final UserRepository userRepository;
    private final UserService userService;

    private User user;

    @Autowired
    UserServiceTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("testuser")
                .email("a@asd.asd")
                .password("dasda")
                .createdAt(LocalDateTime.now())
                .build();
        user = userRepository.save(user);
    }

    @Test
    void testSaveUser() {
        User newUser = User.builder()
                .username("anotherUser")
                .email("asda@asd.asd")
                .password("dasda")
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userService.saveUser(newUser);

        assertNotNull(savedUser.getId());
        assertEquals("anotherUser", savedUser.getUsername());
        assertTrue(userRepository.findById(savedUser.getId()).isPresent());
    }

    @Test
    void testSaveUser_NullUser_ShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.saveUser(null));

        assertEquals("user is null", exception.getMessage());
    }

    @Test
    void testFindUserById() {
        Optional<User> foundUser = userService.findUserById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testFindUserByUsername() {
        Optional<User> foundUser = userService.findUserByUsername("testuser");

        assertTrue(foundUser.isPresent());
        assertEquals(user.getId(), foundUser.get().getId());
    }
}