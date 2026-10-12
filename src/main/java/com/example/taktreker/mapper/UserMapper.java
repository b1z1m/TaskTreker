package com.example.taktreker.mapper;

import com.example.taktreker.dto.UserRequest;
import com.example.taktreker.dto.UserResponse;
import com.example.taktreker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        return new User(
                null,
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password()
        );
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
