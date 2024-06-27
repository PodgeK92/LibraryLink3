package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Page<Book> getBooksPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Optional<Book> getBookById(String isbn) {
        return bookRepository.findById(isbn);
    }
    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public long countBooksAvailable() {
        return bookRepository.countByStatus("AVAILABLE");
    }

    @Transactional
    public void deleteBook(String isbn) {
        System.out.println("Attempting to delete book with ISBN: " + isbn); // Debugging line
        bookRepository.deleteById(isbn);
        System.out.println("Book with ISBN: " + isbn + " should be deleted"); // Debugging line
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }


}
