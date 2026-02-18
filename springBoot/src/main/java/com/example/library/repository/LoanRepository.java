package com.example.library.repository;

import com.example.library.model.Loan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByUserIdAndBookIdAndReturnDateIsNull(
        Long userId,
        Long bookId
    );

    boolean existsByBookIdAndReturnDateIsNull(Long bookId);
}


