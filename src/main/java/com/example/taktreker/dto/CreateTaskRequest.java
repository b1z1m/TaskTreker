package com.example.taktreker.dto;

import com.example.taktreker.Category;
import com.example.taktreker.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTaskRequest {
    @NotNull(message = "Укажите пользователя")
    Long userId;
    @NotBlank(message = "Введите что вы хотите сделать")
    String name;
    String description;
    Category category;
    LocalDateTime deadline;
    TaskStatus status;
}