package ru.yaroglek.naujava.practice3.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yaroglek.naujava.practice3.domain.Task;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskRepository implements CrudRepository<Task, Long>
{
    private final Map<Long, Task> taskContainer;

    @Override
    public void create(Task task)
    {
        taskContainer.put(task.getId(), task);
    }

    @Override
    public Task read(Long id)
    {
        return taskContainer.get(id);
    }

    @Override
    public void update(Task task)
    {
        taskContainer.put(task.getId(), task);
    }

    @Override
    public void delete(Long id)
    {
        taskContainer.remove(id);
    }
}
