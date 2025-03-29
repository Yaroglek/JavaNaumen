package ru.yaroglek.naujava.practice3.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yaroglek.naujava.practice3.domain.Note;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByTask_Id(Long taskId);
    List<Note> findAll();
}
