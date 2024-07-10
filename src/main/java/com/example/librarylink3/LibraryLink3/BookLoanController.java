package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookLoanController {

    @Autowired
    private BookLoanService bookLoanService;

    @GetMapping("/admin/book_checkout")
    public String showBookCheckoutPage(Model model) {
        return "book_checkout";
    }

    @PostMapping("/admin/checkout_book")
    public ResponseEntity<?> checkoutBook(@RequestBody BookLoanRequest bookLoanRequest) {
        try {
            bookLoanService.checkoutBook(bookLoanRequest.getIsbn(), bookLoanRequest.getCardNumberId());
            return ResponseEntity.ok().body(new ApiResponse(true, "Book checked out successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/admin/book_return")
    public String showBookReturnPage(Model model) {
        return "book_return";
    }

    @GetMapping("/user/loans")
    public String showUserLoansPage() {
        return "user_loans";
    }

    @PostMapping("/user/loans")
    public String getUserLoans(@RequestParam("cardNumberId") String cardNumberId, Model model) {
        try {
            List<BookLoan> loans = bookLoanService.getUserLoans(cardNumberId);
            model.addAttribute("loans", loans);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "user_loans";
    }

    @GetMapping("/admin/user_loans")
    public ResponseEntity<?> getUserLoansAdmin(@RequestParam("cardNumberId") String cardNumberId) {
        try {
            List<BookLoan> loans = bookLoanService.getUserLoans(cardNumberId);
            return ResponseEntity.ok(loans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/admin/return_book")
    public ResponseEntity<?> returnBook(@RequestBody BookReturnRequest bookReturnRequest) {
        try {
            bookLoanService.returnBook(bookReturnRequest.getBookLoanId());
            return ResponseEntity.ok().body(new ApiResponse(true, "Book returned successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
