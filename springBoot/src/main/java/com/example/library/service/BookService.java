package com.example.library.service;

import com.example.library.dto.bookDTO.*;
import com.example.library.model.User;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BookResponseDTO createBook(long userId, CreateBookDTO dto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: "+ userId));

        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setUser(user);

        Book saved = bookRepository.save(book);

        return new BookResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor(),
                user.getId()
        );
    }

    public List<BookResponseDTO> findAllBooks(Long userId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: "+ userId));

        return user.getBooks()
                .stream()
                .map(book -> new BookResponseDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        user.getId()
                ))
                .toList();
    }

    public BookResponseDTO findBookById(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: "+ bookId));

        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getUser().getId()
        );
    }

    @Transactional
    public BookResponseDTO updateBook(Long bookId, CreateBookDTO dto){
        Book updateBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: "+ bookId));

        updateBook.setTitle(dto.title());
        updateBook.setAuthor(dto.author());

        Book book = bookRepository.save(updateBook);
        return new BookResponseDTO(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getUser().getId()
        );
    }

    @Transactional
    public void deleteBook(Long bookId){
        Book removedBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: "+ bookId));

        bookRepository.delete(removedBook);
    }
}


