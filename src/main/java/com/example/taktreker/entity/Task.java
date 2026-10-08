package com.example.taktreker.entity;

import com.example.taktreker.Category;
import com.example.taktreker.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    String name;
    String description;
    @Enumerated(EnumType.STRING)
    Category category;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TaskStatus status;
    @ManyToOne
    User user;
    LocalDateTime created;
    LocalDateTime deadline;
}
