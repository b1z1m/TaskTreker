package com.example.taktreker.dto;

import com.example.taktreker.entity.Category;
import com.example.taktreker.entity.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;

public record CreateTaskResponse(
        Long id,

        String name,

        @Schema(nullable = true)
        String description,

        @Schema(nullable = true)
        Category category,

        TaskStatus status,

        UserResponse user,

        LocalDateTime created,

        LocalDateTime deadline
) implements Serializable {
}
