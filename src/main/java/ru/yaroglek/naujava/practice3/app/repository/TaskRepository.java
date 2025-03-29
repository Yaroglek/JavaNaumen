package ru.yaroglek.naujava.practice3.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.yaroglek.naujava.practice3.domain.Task;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    /**
     * Находит все задачи в заданном списке, у которых дата окончания наступает раньше заданной
     * @param todoListId - Id списка.
     * @param dueDateBefore - пороговые дата-время.
     * @return список задач.
     */
    List<Task> findAllByTodoList_IdAndDueDateIsBefore(Long todoListId, LocalDateTime dueDateBefore);

    /**
     * Находит все задачи, принадлежащие заданному пользователю.
     * Поиск осуществляется через связанную сущность TodoList.
     *
     * @param userId - идентификатор пользователя.
     * @return список задач.
     */
    @Query("SELECT t FROM Task t WHERE t.todoList.owner.id = :userId")
    List<Task> findAllTasksByUserId(@Param("userId") Long userId);
}
