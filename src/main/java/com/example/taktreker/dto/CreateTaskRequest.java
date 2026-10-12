package com.example.taktreker.dto;

import com.example.taktreker.entity.Category;
import com.example.taktreker.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTaskRequest(
        @NotNull(message = "Укажите пользователя")
        Long userId,

        @NotBlank(message = "Введите что вы хотите сделать")
        String name,

        String description,

        Category category,

        LocalDateTime deadline,

        TaskStatus status
) {
}
