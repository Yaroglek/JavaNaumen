package ru.yaroglek.naujava.practice3.extern.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yaroglek.naujava.practice3.app.repository.TaskRepository;
import ru.yaroglek.naujava.practice3.extern.assembler.TaskAssembler;
import ru.yaroglek.naujava.practice3.extern.dto.TaskDTO;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/custom/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskAssembler taskAssembler;

    @GetMapping("/findAllUsersTasks")
    public ResponseEntity<List<TaskDTO>>  findAllTasksByUserId(@RequestParam String userId) {
        List<TaskDTO> allUserTasks = taskRepository.findAllTasksByUserId(Long.parseLong(userId)).stream()
                .map(taskAssembler::toModel)
                .toList();

        return ResponseEntity.ok(allUserTasks);
    }

    @GetMapping("/findExpiredTasks")
    public ResponseEntity<List<TaskDTO>>  findAllTasksByTodoListAndDueDateExpire(@RequestParam String listId,
                                                                                 @RequestParam String expireDate) {
        LocalDateTime dueDate = LocalDateTime.parse(expireDate);
        List<TaskDTO> neededTasks = taskRepository.findAllByTodoList_IdAndDueDateIsBefore(Long.parseLong(listId), dueDate).stream()
                .map(taskAssembler::toModel)
                .toList();

        return ResponseEntity.ok(neededTasks);
    }
}
