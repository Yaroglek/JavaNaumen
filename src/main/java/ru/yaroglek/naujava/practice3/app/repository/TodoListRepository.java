package ru.yaroglek.naujava.practice3.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yaroglek.naujava.practice3.domain.TodoList;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList, Long> {
    TodoList getById(Long i);
}
