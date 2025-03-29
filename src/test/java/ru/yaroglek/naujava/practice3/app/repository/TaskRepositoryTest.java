package ru.yaroglek.naujava.practice3.app.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.yaroglek.naujava.practice3.app.repository.criteria.TaskRepositoryImpl;
import ru.yaroglek.naujava.practice3.domain.Task;
import ru.yaroglek.naujava.practice3.domain.TodoList;
import ru.yaroglek.naujava.practice3.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class TaskRepositoryTest {
    private final TaskRepository taskRepository;
    private final TaskRepositoryImpl taskRepositoryImpl;
    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    @Autowired
    TaskRepositoryTest(TaskRepository taskRepository, TaskRepositoryImpl taskRepositoryImpl, TodoListRepository todoListRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskRepositoryImpl = taskRepositoryImpl;
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }

    private User user;
    private TodoList todoList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("testUser")
                .email("test@example.com")
                .passwordHash("hashed_password")
                .build();
        userRepository.save(user);

        todoList = TodoList.builder()
                .title("Test TodoList")
                .owner(user)
                .build();
        todoListRepository.save(todoList);

        task1 = Task.builder()
                .title("Task 1")
                .todoList(todoList)
                .dueDate(LocalDateTime.now().plusDays(1))
                .build();
        taskRepository.save(task1);

        task2 = Task.builder()
                .title("Task 2")
                .todoList(todoList)
                .dueDate(LocalDateTime.now().minusDays(1))
                .build();
        taskRepository.save(task2);
    }


    @Test
    void testFindAllByTodoListAndDueDate() {
        List<Task> overdueTasks = taskRepository.findAllByTodoList_IdAndDueDateIsBefore(todoList.getId(), LocalDateTime.now());

        Assertions.assertEquals(1, overdueTasks.size());
        Assertions.assertEquals("Task 2", overdueTasks.getFirst().getTitle());
    }

    @Test
    void testFindAllTasksByUser() {
        List<Task> userTasks = taskRepository.findAllTasksByUserId(user.getId());

        Assertions.assertEquals(2, userTasks.size());
        Assertions.assertTrue(userTasks.stream().anyMatch(task -> "Task 1".equals(task.getTitle())));
        Assertions.assertTrue(userTasks.stream().anyMatch(task -> "Task 2".equals(task.getTitle())));
    }

    @Test
    void testFindAllByTodoListAndDueDate_CriteriaApi() {
        List<Task> overdueTasks = taskRepositoryImpl.findAllByTodoList_IdAndDueDateIsBefore(todoList.getId(), LocalDateTime.now());

        Assertions.assertEquals(1, overdueTasks.size());
        Assertions.assertEquals("Task 2", overdueTasks.getFirst().getTitle());
    }

    @Test
    void testFindAllTasksByUser_CriteriaApi() {
        List<Task> userTasks = taskRepositoryImpl.findAllTasksByUserId(user.getId());

        Assertions.assertEquals(2, userTasks.size());
        Assertions.assertTrue(userTasks.stream().anyMatch(task -> "Task 1".equals(task.getTitle())));
        Assertions.assertTrue(userTasks.stream().anyMatch(task -> "Task 2".equals(task.getTitle())));
    }
}
