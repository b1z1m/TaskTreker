package com.example.taktreker.dto;

import com.example.taktreker.Category;
import com.example.taktreker.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateTaskResponse implements Serializable {
    Long id;
    String name;
    @Schema(nullable = true)
    String description;
    @Schema(nullable = true)
    Category category;
    TaskStatus status;
    UserResponse user;
    LocalDateTime created;
    LocalDateTime deadline;
}