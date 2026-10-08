package com.example.taktreker.data;

import com.example.taktreker.Category;
import com.example.taktreker.TaskStatus;
import com.example.taktreker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCategory(Category category);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByCategoryAndStatus(Category category, TaskStatus status);

    void deleteByUserId(Long userId);
}