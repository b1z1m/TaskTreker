package com.example.taktreker.dto;

import java.io.Serializable;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) implements Serializable {
}