package com.example.taktreker.controller;

import com.example.taktreker.entity.Category;
import com.example.taktreker.entity.TaskStatus;
import com.example.taktreker.dto.CreateTaskRequest;
import com.example.taktreker.dto.CreateTaskResponse;
import com.example.taktreker.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<CreateTaskResponse> getTasks(@RequestParam(required = false) Category category,
                                             @RequestParam(required = false) TaskStatus status) {
        return taskService.getTasks(category, status);
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
