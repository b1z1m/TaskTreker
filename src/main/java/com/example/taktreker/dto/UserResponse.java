package com.example.taktreker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserResponse implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
}