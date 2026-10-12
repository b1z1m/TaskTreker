package com.example.taktreker.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Введите имя")
        String firstName,

        @NotBlank(message = "Введите фамилию")
        String lastName,

        @NotBlank(message = "Введите почту")
        String email,

        @NotBlank(message = "Введите пароль")
        String password
) {
}
