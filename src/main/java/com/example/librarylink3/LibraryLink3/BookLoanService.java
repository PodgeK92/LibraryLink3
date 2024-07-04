package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
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

        if (!"Available".equals(book.getStatus())) {
            throw new Exception("Book is not available");
        }

        // Create and save the book loan
        BookLoan bookLoan = new BookLoan();
        bookLoan.setBook(book);
        bookLoan.setUser(user);
        bookLoan.setLoanDate(LocalDate.now());
        bookLoan.setReturnDate(LocalDate.now().plusWeeks(2)); // Example: 2-week loan period
        bookLoan.setRenewalsNumber(0);

        bookLoanRepository.save(bookLoan);

        // Update book status to "Unavailable"
        book.setStatus("Unavailable");
        bookRepository.save(book);
    }

    public List<BookLoan> getUserLoans(String cardNumberId) {
        return bookLoanRepository.findByUserCardNumberId(cardNumberId);
    }

    @Transactional
    public void returnBook(int bookLoanId) throws Exception {
        Optional<BookLoan> loanOpt = bookLoanRepository.findById(bookLoanId);
        if (!loanOpt.isPresent()) {
            throw new Exception("Loan record not found");
        }

        BookLoan loan = loanOpt.get();
        loan.setReturnedOnDate(LocalDate.now());
        bookLoanRepository.save(loan);

        // Update book status to "Available"
        Book book = loan.getBook();
        book.setStatus("Available");
        bookRepository.save(book);
    }
}
