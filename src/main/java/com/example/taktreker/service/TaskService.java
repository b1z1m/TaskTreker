package com.example.taktreker.service;

import com.example.taktreker.Category;
import com.example.taktreker.TaskStatus;
import com.example.taktreker.data.TaskRepository;
import com.example.taktreker.dto.CreateTaskRequest;
import com.example.taktreker.dto.CreateTaskResponse;
import com.example.taktreker.entity.Task;
import com.example.taktreker.entity.User;
import com.example.taktreker.exception.NotFoundException;
import com.example.taktreker.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    @CacheEvict(value = "taskLists", allEntries = true)
    public CreateTaskResponse createTask(CreateTaskRequest request) {
        User user = userService.getUser(request.getUserId());

        Task task = taskMapper.toEntity(request, user);
        task.setCreated(LocalDateTime.now());
        task.setStatus(request.getStatus() != null ? request.getStatus() : TaskStatus.TODO);

        Task saved = taskRepository.save(task);
        log.info("Создана задача id={} для пользователя id={}", saved.getId(), user.getId());

        return taskMapper.toResponse(saved);
    }

    @Cacheable("tasks")
    public CreateTaskResponse getTaskById(Long id) {
        log.info("Задача id={} загружается из БД", id);
        return taskMapper.toResponse(getTask(id));
    }

    @Cacheable("taskLists")
    public List<CreateTaskResponse> getTasks(Category category, TaskStatus status) {
        log.info("Список задач загружается из БД: category={}, status={}", category, status);

        List<Task> tasks;
        if (category != null && status != null) {
            tasks = taskRepository.findByCategoryAndStatus(category, status);
        } else if (category != null) {
            tasks = taskRepository.findByCategory(category);
        } else if (status != null) {
            tasks = taskRepository.findByStatus(status);
        } else {
            tasks = taskRepository.findAll();
        }

        return tasks.stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Caching(evict = {
            @CacheEvict(value = "tasks", key = "#id"),
            @CacheEvict(value = "taskLists", allEntries = true)
    })
    public CreateTaskResponse updateTask(Long id, CreateTaskRequest request) {
        Task task = getTask(id);
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setCategory(request.getCategory());
        task.setDeadline(request.getDeadline());
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        Task saved = taskRepository.save(task);
        log.info("Обновлена задача id={}, статус {}", id, saved.getStatus());

        return taskMapper.toResponse(saved);
    }

    @Caching(evict = {
            @CacheEvict(value = "tasks", key = "#id"),
            @CacheEvict(value = "taskLists", allEntries = true)
    })
    public void deleteTask(Long id) {
        Task task = getTask(id);
        taskRepository.delete(task);
        log.info("Удалена задача id={}", id);
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Задача с id " + id + " не найдена"));
    }
}
