package ru.yaroglek.naujava.practice3.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yaroglek.naujava.practice3.app.repository.TaskRepository;
import ru.yaroglek.naujava.practice3.domain.Task;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public void createTask(Long id, String title, String description, LocalDateTime dueDateTime) {
        Task newTask = Task.builder()
                .id(id)
                .title(title)
                .description(description)
                .taskStatus(Task.TaskStatus.TODO)
                .startDateTime(LocalDateTime.now())
                .dueDateTime(dueDateTime)
                .build();

        taskRepository.create(newTask);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public void updateStatus(Long id, Task.TaskStatus newStatus) {
        taskRepository.update(
                taskRepository.read(id).toBuilder().taskStatus(newStatus).build()
        );
    }

    @Override
    public void updateDueDateTime(Long id, LocalDateTime dueDateTime) {
        taskRepository.update(
                taskRepository.read(id).toBuilder().dueDateTime(dueDateTime).build()
        );
    }
}
