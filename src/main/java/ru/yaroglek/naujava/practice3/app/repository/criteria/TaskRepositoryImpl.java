package ru.yaroglek.naujava.practice3.app.repository.criteria;


import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.yaroglek.naujava.practice3.domain.Task;
import ru.yaroglek.naujava.practice3.domain.TodoList;
import ru.yaroglek.naujava.practice3.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepositoryCustom {
    private final EntityManager em ;

    public TaskRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Task> findAllByTodoList_IdAndDueDateIsBefore(Long todoListId, LocalDateTime dueDateBefore) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> task = query.from(Task.class);

        Predicate byTodoList = cb.equal(task.get("todoList").get("id"), todoListId);
        Predicate byDueDate = cb.lessThan(task.get("dueDate"), dueDateBefore);

        query.select(task).where(cb.and(byTodoList, byDueDate));

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Task> findAllTasksByUserId(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> task = query.from(Task.class);

        Join<Task, TodoList> todoListJoin = task.join("todoList");
        Join<TodoList, User> userJoin = todoListJoin.join("owner");

        Predicate byUser = cb.equal(userJoin.get("id"), userId);
        query.select(task).where(byUser);

        return em.createQuery(query).getResultList();
    }
}
