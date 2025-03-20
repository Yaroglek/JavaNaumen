package ru.yaroglek.naujava.practice3.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        COMPLETED
    }
}
