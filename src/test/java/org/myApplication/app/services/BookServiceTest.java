package org.myApplication.app.services;

import lombok.var;
import org.junit.jupiter.api.Test;
import org.myApplication.app.entity.BookEntity;
import org.myApplication.app.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
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
    private BookServiceImpl bookService;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void saveBook() {
        UserEntity user = userOne();
        userService.saveUser(user);
        BookEntity dbBook = bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user), 1L);
        assertNotNull(dbBook);
        assertEquals("Alice in Wonderland", dbBook.getTitle());
    }


    @Test
    void deleteBookById() {
        var user = userService.saveUser(new UserEntity(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        BookEntity book = new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user);
        book = bookService.saveBook(book, 1L);
        bookService.deleteBookById(1L);
        var dbBook = bookService.findBookById(1L);
        assertEquals(Optional.empty(), dbBook);
    }

    @Test
    void findBookById() {
        var user = userService.saveUser(new UserEntity(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user), 1L);
        var dbBook = bookService.findBookById(1L);
        assertNotNull(dbBook);
        assertEquals("Alice in Wonderland", dbBook.get().getTitle());
    }

    @Test
    void changeBook() {
        var user = userService.saveUser(new UserEntity(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        var book = bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user), 1L);
        book.setTitle("Измененное название");
        bookService.changeBook(book, 1L);
        assertEquals("Измененное название", bookService.findBookById(1L).get().getTitle());
    }

    @Test
    void findAllBooks() {
        var user = userService.saveUser(new UserEntity(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user), 1L);
        bookService.saveBook(new BookEntity(2L, "Book 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", user), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", user), 1L);
        var result = bookService.findAllBooks(0, 2, "publicationYear", "ASC");
        assertEquals(2, result.size());
        assertEquals("Alice in Wonderland", result.get(0).getTitle());
        assertEquals("Book 3", result.get(1).getTitle());
    }

    @Test
    void searchBooks() {
        userService.saveUser(new UserEntity(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book Alice 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var result = bookService.searchBooks("alic", 0, 2, "publicationYear", "ASC");
        assertEquals(2, result.size());
        assertEquals("Alice in Wonderland", result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    private static UserEntity userOne() {
        return new UserEntity(1L, "Bob", "boB7523", "@BobTg", "Moscow",
                "Oktyabrsky", new ArrayList<>());
    }
}