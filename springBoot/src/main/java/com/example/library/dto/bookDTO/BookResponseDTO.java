package com.example.library.dto.bookDTO;

public record BookResponseDTO(
        Long id,
        String title,
        String author,
        Long userId
){}

