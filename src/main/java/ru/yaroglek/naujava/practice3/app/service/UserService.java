package ru.yaroglek.naujava.practice3.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yaroglek.naujava.practice3.app.repository.UserRepository;
import ru.yaroglek.naujava.practice3.domain.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Добавляет пользователя в БД. Если пользователь - null, кидает исключение.
     * @param user - пользователь для добавления
     * @return - добавленный пользователь
     */
    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    /**
     * Ищет пользователя по его ID
     * @param id - ID для поиска
     * @return - найденный пользователь в обертке Optional
     */
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    /**
     * Ищет пользователя по его имени
     * @param username - имя для поиска
     * @return - найденный пользователь в обертке Optional
     */
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Удаляет пользователя по id
     * @param id - id для удаления
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
