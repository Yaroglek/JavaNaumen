package ru.yaroglek.naujava.practice3.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.yaroglek.naujava.practice3.domain.User;

import java.util.Optional;

@Repository
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);
}
