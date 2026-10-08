package com.example.taktreker.service;

import com.example.taktreker.data.TaskRepository;
import com.example.taktreker.data.UserRepository;
import com.example.taktreker.dto.UserRequest;
import com.example.taktreker.dto.UserResponse;
import com.example.taktreker.entity.User;
import com.example.taktreker.exception.EmailAlreadyExistsException;
import com.example.taktreker.exception.NotFoundException;
import com.example.taktreker.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User saved = userRepository.save(userMapper.toEntity(request));
        log.info("Создан пользователь id={}", saved.getId());

        return userMapper.toResponse(saved);
    }

    @Cacheable("users")
    public UserResponse getUserById(Long id) {
        log.info("Пользователь id={} загружается из БД", id);
        return userMapper.toResponse(getUser(id));
    }

    @Caching(evict = {
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "tasks", allEntries = true),
            @CacheEvict(value = "taskLists", allEntries = true)
    })
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = getUser(id);

        boolean emailChanged = !user.getEmail().equals(request.getEmail());
        if (emailChanged && userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User saved = userRepository.save(user);
        log.info("Обновлён пользователь id={}", id);

        return userMapper.toResponse(saved);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "tasks", allEntries = true),
            @CacheEvict(value = "taskLists", allEntries = true)
    })
    public void deleteUser(Long id) {
        User user = getUser(id);
        taskRepository.deleteByUserId(id);
        userRepository.delete(user);
        log.info("Удалён пользователь id={} вместе с его задачами", id);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + id + " не найден"));
    }
}
