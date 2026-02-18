package com.example.library.dto.userDTO;
import jakarta.validation.constraints.*;

public record CreateUserDTO(
        @Size(max = 100)
        @NotBlank(message = "Name must not be empty")
        String name,

        @Size(max = 150)
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email must not be empty")
        String email
){}
