package com.example.taktreker.controller;

import com.example.taktreker.entity.Category;
import com.example.taktreker.entity.TaskStatus;
import com.example.taktreker.dto.CreateTaskRequest;
import com.example.taktreker.dto.CreateTaskResponse;
import com.example.taktreker.dto.PageResponse;
import com.example.taktreker.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public CreateTaskResponse createTask(@RequestBody @Valid CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public PageResponse<CreateTaskResponse> getTasks(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) TaskStatus status,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return taskService.getTasks(category, status, pageable);
    }

    @GetMapping("/{id}")
    public CreateTaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public CreateTaskResponse updateTask(@PathVariable Long id, @RequestBody @Valid CreateTaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
