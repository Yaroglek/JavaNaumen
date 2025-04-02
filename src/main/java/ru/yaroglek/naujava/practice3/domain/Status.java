package ru.yaroglek.naujava.practice3.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Сущность статуса задачи.
 */
@Entity
@Table(name = "statuses")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "status")
    private List<Task> tasks;
}
