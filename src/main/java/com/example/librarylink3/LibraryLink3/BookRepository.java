package com.example.librarylink3.LibraryLink3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, String> {
    long countByStatus(String status);
}
