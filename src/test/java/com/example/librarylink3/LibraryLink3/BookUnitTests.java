package com.example.librarylink3.LibraryLink3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class BookUnitTests {

    @Autowired
    private BookService bookService;

    @Test
    public void testAddNewBook() {
        String isbn = "9780553902549";
        String title = "Journey to the Centre of the Earth";
        String author = "Jules Verne";
        int publishedYear = 2001;
        String genre = "Fiction";
        String format = "paperback";
        Book newBook = new Book(isbn, title, author, publishedYear, genre, format);

        Book addedBook = bookService.addBook(newBook);
        Optional<Book> retrievedBook = bookService.getBookById(isbn);

        assertTrue(retrievedBook.get().getAuthor().contains(author));
    }

}
