package ru.yaroglek.naujava.practice3.app.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yaroglek.naujava.practice3.app.repository.NoteRepository;
import ru.yaroglek.naujava.practice3.app.repository.TaskRepository;
import ru.yaroglek.naujava.practice3.domain.Note;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final NoteRepository noteRepository;

    /**
     * Удаляет задачу по ее Id, попутно удаляя все относящиеся к ней заметки.
     * @param taskId - Id задачи
     */
    @Transactional
    public void deleteTaskById(Long taskId) {
        List<Note> notes = noteRepository.findByTask_Id(taskId);
        noteRepository.deleteAll(notes);
        taskRepository.deleteById(taskId);
    }
}
