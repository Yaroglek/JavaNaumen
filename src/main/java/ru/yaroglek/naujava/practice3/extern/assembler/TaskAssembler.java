package ru.yaroglek.naujava.practice3.extern.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.yaroglek.naujava.practice3.domain.Note;
import ru.yaroglek.naujava.practice3.domain.Task;
import ru.yaroglek.naujava.practice3.extern.controller.TaskController;
import ru.yaroglek.naujava.practice3.extern.dto.TaskDTO;

@Component
public class TaskAssembler extends RepresentationModelAssemblerSupport<Task, TaskDTO> {
    public TaskAssembler() {
        super(TaskController.class, TaskDTO.class);
    }

    @Override
    public TaskDTO toModel(Task task) {
        TaskDTO dto = instantiateModel(task);

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setDueDate(task.getDueDate());
        dto.setTodoListId(task.getTodoList().getId());
        dto.setStatusId(task.getStatus().getId());
        dto.setNotesIds(task.getNotes().stream()
                .map(Note::getId)
                .toList());

        return dto;
    }
}
