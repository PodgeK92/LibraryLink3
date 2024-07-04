package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookLoanService {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public void checkoutBook(String isbn, String cardNumberId) throws Exception {
        // Validate user and book existence
        Optional<User> userOpt = userRepository.findById(cardNumberId);
        Optional<Book> bookOpt = bookRepository.findById(isbn);

        if (!userOpt.isPresent()) {
            throw new Exception("User not found");
        }

        if (!bookOpt.isPresent()) {
            throw new Exception("Book not found");
        }

        User user = userOpt.get();
        Book book = bookOpt.get();

        // Create and save the book loan
        BookLoan bookLoan = new BookLoan();
        bookLoan.setBook(book);
        bookLoan.setUser(user);
        bookLoan.setLoanDate(LocalDate.now());
        bookLoan.setReturnDate(LocalDate.now().plusWeeks(2)); // Example: 2-week loan period
        bookLoan.setRenewalsNumber(0);

        bookLoanRepository.save(bookLoan);
    }
}
