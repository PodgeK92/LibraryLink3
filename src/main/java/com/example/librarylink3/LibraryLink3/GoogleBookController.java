package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/googlebooks")
public class GoogleBookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/checkAvailability")
    public ResponseEntity<Boolean> checkBookAvailability(@RequestParam String isbn) {
        boolean isAvailable = bookService.checkBookAvailability(isbn);
        return ResponseEntity.ok(isAvailable);
    }
}

