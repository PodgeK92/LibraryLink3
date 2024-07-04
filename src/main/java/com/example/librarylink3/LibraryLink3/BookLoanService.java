package com.example.librarylink3.LibraryLink3;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BookLoanService {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public boolean isBookAvailable(String isbn) {
        return bookRepository.existsByIsbnAndStatus(isbn, "Available");
    }

    @Transactional
    public void checkoutBook(String isbn, String cardNumberId) {
        // Create a new BookLoan
        BookLoan bookLoan = new BookLoan();
        bookLoan.setIsbn(isbn);
        bookLoan.setCardNumberId(cardNumberId);
        bookLoan.setLoanDate(LocalDate.now());

        bookLoanRepository.save(bookLoan);

        // Update book status to "Unavailable"
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setStatus("Unavailable");
            bookRepository.save(book);
        } else {
            // Handle the case where the book is not found
            throw new RuntimeException("Book not found with ISBN: " + isbn);
        }

    }
}
