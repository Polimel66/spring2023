package org.myApplication.app.services;

import lombok.var;
import org.junit.jupiter.api.Test;
import org.myApplication.app.Book;
import org.myApplication.app.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Test
    void saveBook() {
        User user = new User(1L, "Bob", "boB7523", "@BobTg", "Moscow",
                "Oktyabrsky", new ArrayList<>());
        userService.saveUser(user);
        Book dbBook = bookService.saveBook(new Book(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        assertNotNull(dbBook);
        assertEquals("Alice in Wonderland", dbBook.getTitle());
    }

    @Test
    void deleteBookById() {
        var user = userService.saveUser(new User(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        bookService.saveBook(new Book(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        bookService.deleteBookById(1L);
        var dbBook = bookService.findBookById(1L);
        assertEquals(Optional.empty(), dbBook);
    }

    @Test
    void findBookById() {
        var user = userService.saveUser(new User(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        bookService.saveBook(new Book(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        var dbBook = bookService.findBookById(1L);
        assertNotNull(dbBook);
        assertEquals("Alice in Wonderland", dbBook.get().getTitle());
    }

    @Test
    void changeBook() {
        var user = userService.saveUser(new User(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        var book = bookService.saveBook(new Book(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        book.setTitle("Измененное название");
        bookService.changeBook(book);
        assertEquals("Измененное название", bookService.findBookById(1L).get().getTitle());
    }

    @Test
    void findAllBooks() {
        var user = userService.saveUser(new User(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        bookService.saveBook(new Book(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        bookService.saveBook(new Book(2L, "Book 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", user));
        bookService.saveBook(new Book(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", user));
        var result = bookService.findAllBooks(PageRequest.of(0, 2));
        assertEquals(2, result.size());
        assertEquals("Alice in Wonderland", result.get(0).getTitle());
        assertEquals("Book 2", result.get(1).getTitle());
    }
}