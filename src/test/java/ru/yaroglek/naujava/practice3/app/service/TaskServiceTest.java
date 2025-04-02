package ru.yaroglek.naujava.practice3.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import ru.yaroglek.naujava.practice3.app.repository.NoteRepository;
import ru.yaroglek.naujava.practice3.app.repository.TaskRepository;
import ru.yaroglek.naujava.practice3.domain.Note;
import ru.yaroglek.naujava.practice3.domain.Task;


@DataJpaTest
@ComponentScan("ru.yaroglek.naujava.practice3.app")
class TaskServiceTest {
    private final TaskRepository taskRepository;
    private final NoteRepository noteRepository;
    private final TaskService taskService;

    @Autowired
    TaskServiceTest(TaskRepository taskRepository, NoteRepository noteRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.noteRepository = noteRepository;
        this.taskService = taskService;
    }

    private Task task;
    private Note note;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .title("Task1")
                .build();
        task = taskRepository.save(task);

        note = Note.builder()
                .content("Note 1")
                .task(task)
                .build();
        noteRepository.save(note);
    }

    @Test
    void testDeleteTaskById() {
        Long taskId = task.getId();

        Assertions.assertEquals(1, noteRepository.findAll().size());
        taskService.deleteTaskById(taskId);

        Assertions.assertFalse(taskRepository.findById(taskId).isPresent());
        Assertions.assertEquals(0, noteRepository.findAll().size());
    }

    @Test
    void testDeleteTaskById_TaskNotFound_ShouldNotThrowException() {
        Long nonExistentTaskId = 999L;

        Assertions.assertDoesNotThrow(() -> taskService.deleteTaskById(nonExistentTaskId));
    }
}