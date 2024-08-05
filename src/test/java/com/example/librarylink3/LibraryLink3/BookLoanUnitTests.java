package com.example.librarylink3.LibraryLink3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class BookLoanUnitTests {

    @Autowired
    private BookLoanService bookLoanService;

    @Autowired
    private BookService bookService;


    @Test
    public void testCheckoutBook() {
        //assuming following data exists. Alternatively use test setup to add this data to the database
        String userCardNumberId = "G19286271";
        String isbn = "1234";

        try {
            bookLoanService.checkoutBook(isbn, userCardNumberId);

            Optional<Book> retrievedBook = bookService.getBookById(isbn);
            assertTrue(retrievedBook.get().getStatus().contains("Unavailable"));
        } catch (Exception ignored) {
        }
    }


    @Test
    public void testReturnBook() {
        //assuming following data exists. Alternatively use test setup to add this data to the database
        String userCardNumberId = "G19286271";
        String isbn = "1234";
        List<BookLoan> bookLoans = bookLoanService.getUserLoans(userCardNumberId);
        BookLoan loan = bookLoans.get(1);

        try {
            bookLoanService.returnBook(loan.getBookLoanId());

            Optional<Book> retrievedBook = bookService.getBookById(isbn);
            assertTrue(retrievedBook.get().getStatus().contains("Available"));
        } catch (Exception ignored) {
        }
    }
}
