package com.example.library.controller;

import com.example.library.dto.bookDTO.BookResponseDTO;
import com.example.library.dto.bookDTO.CreateBookDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.library.service.BookService;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
        public ResponseEntity<BookResponseDTO> create(
                @PathVariable Long userId,
                @Valid @RequestBody CreateBookDTO dto){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.createBook(userId, dto));
    }

    @GetMapping
        public ResponseEntity<List<BookResponseDTO>> findAll(
                @PathVariable Long userId
    ){
            return ResponseEntity.ok(bookService.findAllBooks(userId));
    }

    @GetMapping("/{bookId}")
        public ResponseEntity<BookResponseDTO> findById(@PathVariable Long bookId){
            return ResponseEntity.ok(bookService.findBookById(bookId));
    }

    @PutMapping("/{bookId}")
        public ResponseEntity<BookResponseDTO> update(
                @PathVariable Long bookId,
                @Valid @RequestBody CreateBookDTO dto
    ){
                return ResponseEntity.ok(bookService.updateBook(bookId, dto));
    }

    @DeleteMapping("/{bookId}")
        public ResponseEntity<Void> delete(
                @PathVariable Long bookId){
            bookService.deleteBook(bookId);
            return ResponseEntity.noContent().build();
    }
}

