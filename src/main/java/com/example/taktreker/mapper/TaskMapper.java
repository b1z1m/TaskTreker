package com.example.taktreker.mapper;

import com.example.taktreker.dto.CreateTaskRequest;
import com.example.taktreker.dto.CreateTaskResponse;
import com.example.taktreker.entity.Task;
import com.example.taktreker.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final UserMapper userMapper;

    public Task toEntity(CreateTaskRequest request, User user) {
        Task task = new Task();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setCategory(request.getCategory());
        task.setDeadline(request.getDeadline());
        task.setUser(user);
        return task;
    }

    public CreateTaskResponse toResponse(Task task) {
        return new CreateTaskResponse(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getCategory(),
                task.getStatus(),
                userMapper.toResponse(task.getUser()),
                task.getCreated(),
                task.getDeadline()
        );
    }
}
