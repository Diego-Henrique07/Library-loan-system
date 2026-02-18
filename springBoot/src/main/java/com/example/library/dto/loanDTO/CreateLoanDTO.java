package com.example.library.dto.loanDTO;

import jakarta.validation.constraints.*;

public record CreateLoanDTO(
        @NotBlank Long user_id,
        @NotBlank Long book_id
){}
