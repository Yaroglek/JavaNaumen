package ru.yaroglek.naujava.practice3.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.yaroglek.naujava.practice3.app.repository.UserRepository;
import ru.yaroglek.naujava.practice3.domain.User;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        User toSave = User.builder()
                .id(1L)
                .username("test")
                .password("plainPassword")
                .build();

        String encodedPassword = "encodedPassword";

        when(bCryptPasswordEncoder.encode("plainPassword")).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.saveUser(toSave);

        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(encodedPassword, savedUser.getPassword());
        Assertions.assertEquals(toSave.getId(), savedUser.getId());
        verify(bCryptPasswordEncoder).encode("plainPassword");
        verify(userRepository).save(toSave);
    }

    @Test
    public void testUserNotCreated() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.saveUser(null));

        Assertions.assertEquals("user is null", exception.getMessage());
        verifyNoInteractions(userRepository);
    }

    @Test
    public void testFindUserById() {
        User toFind = User.builder()
                .id(1L)
                .build();

        when(userRepository.findUserById(1L)).thenReturn(Optional.of(toFind));

        User result = userService.findUserById(1L).orElse(null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    public void testUserNotFoundById() {
        when(userRepository.findUserById(1L)).thenReturn(Optional.empty());

        User result = userService.findUserById(1L).orElse(null);

        Assertions.assertNull(result);
    }

    @Test
    public void testFindByUsername_success() {
        User toFind = User.builder()
                .username("test")
                .build();

        when(userRepository.findUserByUsername("test")).thenReturn(Optional.of(toFind));

        User result = userService.findUserByUsername("test").orElse(null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("test", result.getUsername());
    }

    @Test
    public void testFindByUsername_notFound() {
        when(userRepository.findUserByUsername("missing")).thenReturn(Optional.empty());

        User result = userService.findUserByUsername("missing").orElse(null);

        Assertions.assertNull(result);
    }
}