package ru.yaroglek.naujava.practice3.app.repository.criteria;

import ru.yaroglek.naujava.practice3.domain.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepositoryCustom {
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
    List<Task> findAllTasksByUserId(Long userId);
}
