package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BookLoanController {

    @Autowired
    private BookLoanService bookLoanService;

    @GetMapping("/admin/book_checkout")
    public String showBookCheckoutPage(Model model) {
        return "book_checkout";
    }

    @PostMapping("/admin/checkout_book")
    @ResponseBody
    public ResponseEntity<Map<String, String>> checkoutBook(
            @RequestParam("isbn") String isbn,
            @RequestParam("cardNumberId") String cardNumberId) {

        boolean isBookAvailable = bookLoanService.isBookAvailable(isbn);

        if (!isBookAvailable) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "Book is not available.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            bookLoanService.checkoutBook(isbn, cardNumberId);
            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "Book checked out successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "Failed to check out book: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
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
