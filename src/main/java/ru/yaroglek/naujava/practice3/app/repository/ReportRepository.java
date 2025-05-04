package ru.yaroglek.naujava.practice3.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yaroglek.naujava.practice3.domain.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
}
