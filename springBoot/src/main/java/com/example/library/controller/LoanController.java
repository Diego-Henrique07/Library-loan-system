package com.example.library.controller;

import com.example.library.model.Loan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.library.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping("/users/{userId}/books/{bookId}")
        public ResponseEntity<Loan> borrowBook(
                @PathVariable Long userId,
                @PathVariable Long bookId
        ){
            Loan loan = loanService.createLoan(userId, bookId);
            return ResponseEntity.ok(loan);
    }

    @PutMapping("/{loanId}/return")
       public ResponseEntity<Loan> returnBook(@PathVariable Long loanId){
        Loan loan = loanService.returnBook(loanId);
        return ResponseEntity.ok(loan);
    }
}
