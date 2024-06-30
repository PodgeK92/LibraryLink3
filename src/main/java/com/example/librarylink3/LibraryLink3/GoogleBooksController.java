package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/google-books")
public class GoogleBooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("/checkAvailability")
    public boolean checkAvailability(@RequestParam String isbn) {
        String[] isbns = isbn.split(",\\s*");
        for (String singleIsbn : isbns) {
            if (bookService.checkBookAvailability(singleIsbn)) {
                return true;
            }
        }
        return false;
    }
}

