package com.example.librarylink3.LibraryLink3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {
}

