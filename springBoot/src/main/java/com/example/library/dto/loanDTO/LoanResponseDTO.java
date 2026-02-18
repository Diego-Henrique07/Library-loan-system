package com.example.library.dto.loanDTO;

import java.time.LocalDateTime;

public record LoanResponseDTO(
    Long id,
    Long userId,
    Long Bookid,
    LocalDateTime borrowDate,
    LocalDateTime returnDate
){}
