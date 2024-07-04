package com.example.librarylink3.LibraryLink3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {
    Optional<BookLoan> findByBookAndReturnedOnDateIsNull(Book book);

    List<BookLoan> findByUserCardNumberId(String cardNumberId);
}

