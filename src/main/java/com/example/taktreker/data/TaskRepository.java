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

import java.time.LocalDateTime;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @EntityGraph(attributePaths = "user")
    @Query("""
            select t from Task t
            where (:status is null or t.status = :status)
              and (:category is null or t.category = :category)
              and (:from is null or t.created >= :from)
              and (:to   is null or t.created <= :to)         
            """)
    Page<Task> search(@Param("status") TaskStatus status,
                      @Param("category") Category category,
                      @Param("from") LocalDateTime from,
                      @Param("to") LocalDateTime to,
                      Pageable pageable);

    @Modifying
    @Query("delete from Task t where t.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
