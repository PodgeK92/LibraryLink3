package com.example.librarylink3.LibraryLink3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, String> {
    long countByStatus(String status);

    boolean existsByIsbn(String isbn);

    boolean existsByIsbnAndStatus(String isbn, String status);

    Optional<Book> findByIsbn(String isbn);

    @Modifying
    @Query("UPDATE Book b SET b.status = :status WHERE b.isbn = :isbn")
    void updateBookStatus(@Param("isbn") String isbn, @Param("status") String status);
}
