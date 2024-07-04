package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
            return ResponseEntity.ok().body(new ApiResponse("Book checked out successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }

    // Helper classes for request and response
    public static class BookLoanRequest {
        private String isbn;
        private String cardNumberId;

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getCardNumberId() {
            return cardNumberId;
        }

        public void setCardNumberId(String cardNumberId) {
            this.cardNumberId = cardNumberId;
        }


    }

    public static class ApiResponse {
        private String message;

        public ApiResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


    }
}
