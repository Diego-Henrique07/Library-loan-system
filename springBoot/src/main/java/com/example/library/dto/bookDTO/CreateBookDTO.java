package com.example.library.dto.bookDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBookDTO(
        @Size(max = 150)
        @NotBlank (message = "Title must not be empty")
        String title,

        @Size(max = 100)
        @NotBlank(message = "Author must not be empty")
        String author
){}

