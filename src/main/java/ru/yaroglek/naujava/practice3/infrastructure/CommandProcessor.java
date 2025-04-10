package ru.yaroglek.naujava.practice3.infrastructure;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yaroglek.naujava.practice3.app.service.TaskService;
import ru.yaroglek.naujava.practice3.domain.Task;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CommandProcessor
{
    private final TaskService taskService;

    public void processCommand(String input)
    {
        String[] cmd = input.split("\\|");
        switch (cmd[0])
        {
            case "create" -> {
                taskService.createTask(Long.valueOf(cmd[1]), cmd[2], cmd[3], LocalDateTime.parse(cmd[4]));
                System.out.println("Задача успешно создана.");
            }
            case "get" -> {
                Task toGet = taskService.findById(Long.valueOf(cmd[1]));
                System.out.println(toGet == null ? "Такой задачи не существует" : toGet);
            }
            case "delete" -> {
                taskService.deleteById(Long.valueOf(cmd[1]));
                System.out.println("Задача успешно удалена.");
            }
            case "status" -> {
                taskService.updateStatus(Long.valueOf(cmd[1]), Task.TaskStatus.valueOf(cmd[2]));
                System.out.println("Статус успешно обновлен.");
            }
            case "dueDateTime" -> {
                taskService.updateDueDateTime(Long.valueOf(cmd[1]), LocalDateTime.parse(cmd[2]));
                System.out.println("Дата и время окончания успешно обновлены.");
            }
            default -> System.out.println("Введена неизвестная команда.");
        }
    }
}

