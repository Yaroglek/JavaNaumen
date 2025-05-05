package ru.yaroglek.naujava.practice3.extern.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaskDTO extends RepresentationModel<TaskDTO> {
    private long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private long todoListId;
    private long statusId;
    private List<Long> notesIds;
}
