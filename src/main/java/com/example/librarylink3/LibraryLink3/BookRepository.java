package com.example.librarylink3.LibraryLink3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository <Book, String> {
    long countByStatus(String status);

    boolean existsByIsbn(String isbn);

    @Query("SELECT COUNT(b) > 0 FROM Book b WHERE b.isbn = :isbn AND b.status = 'Available'")
    boolean isBookAvailable(@Param("isbn") String isbn);
}
