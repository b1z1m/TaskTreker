package com.example.taktreker.data;

import com.example.taktreker.entity.Category;
import com.example.taktreker.entity.Task;
import com.example.taktreker.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @EntityGraph(attributePaths = "user")
    Page<Task> findByCategory(Category category, Pageable pageable);

    @EntityGraph(attributePaths = "user")
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    @EntityGraph(attributePaths = "user")
    Page<Task> findByCategoryAndStatus(Category category, TaskStatus status, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "user")
    Page<Task> findAll(Pageable pageable);

    @Modifying
    @Query("delete from Task t where t.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
