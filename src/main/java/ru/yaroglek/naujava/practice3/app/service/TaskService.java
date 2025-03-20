package ru.yaroglek.naujava.practice3.app.service;

import ru.yaroglek.naujava.practice3.domain.Task;

import java.time.LocalDateTime;

public interface TaskService
{
    void createTask(Long id, String title, String description, LocalDateTime dueDateTime);
    Task findById(Long id);
    void deleteById(Long id);
    void updateStatus(Long id, Task.TaskStatus newStatus);
    void updateDueDateTime(Long id, LocalDateTime dueDateTime);
}

