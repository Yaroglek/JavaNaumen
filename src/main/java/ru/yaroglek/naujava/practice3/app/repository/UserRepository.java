package ru.yaroglek.naujava.practice3.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yaroglek.naujava.practice3.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
