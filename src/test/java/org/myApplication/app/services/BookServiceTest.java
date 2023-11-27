package org.myApplication.app.services;

import org.junit.jupiter.api.Test;
import org.myApplication.app.entity.BookEntity;
import org.myApplication.app.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        userService.saveUser(userOne());
        BookEntity dbBook = bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        assertNotNull(dbBook);
        assertEquals("Alice in Wonderland", dbBook.getTitle());
    }


    @Test
    void deleteBookById() {
        userService.saveUser(userOne());
        BookEntity book = new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null);
        bookService.saveBook(book, 1L);
        bookService.deleteBookById(1L);
        var dbBook = bookService.findBookById(1L);
        assertEquals(Optional.empty(), dbBook);
    }

    @Test
    void findBookById() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        var dbBook = bookService.findBookById(1L);
        assertNotNull(dbBook);
        assertEquals("Alice in Wonderland", dbBook.get().getTitle());
    }

    @Test
    void changeBook() {
        userService.saveUser(userOne());
        var book = bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        book.setTitle("Измененное название");
        bookService.changeBook(book, 1L);
        assertEquals("Измененное название", bookService.findBookById(1L).get().getTitle());
    }

    @Test
    void findAllBooks() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        var result = bookService.findAllBooks(PageRequest.of(0, 2, Sort.by("publicationYear").ascending()));
        assertEquals(2, result.size());
        assertEquals("Alice in Wonderland", result.get(0).getTitle());
        assertEquals("Book 3", result.get(1).getTitle());
    }

    @Test
    void searchBooks() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book Alice 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var result = bookService.searchBooks("alic", PageRequest.of(0, 2, Sort.by("publicationYear").ascending()));
        assertEquals(2, result.size());
        assertEquals("Alice in Wonderland", result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    @Test
    void filterBooksLE() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                2000, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book Alice 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var criteriaModel = new ArrayList<String>();
        criteriaModel.add("publicationYear");
        criteriaModel.add("LE");
        criteriaModel.add("2001");
        var result = bookService.filterBooks(criteriaModel);
        assertEquals(2, result.size());
        assertEquals("Alice in Wonderland", result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    @Test
    void filterBooksLT() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                2000, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book Alice 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var criteriaModel = new ArrayList<String>();
        criteriaModel.add("publicationYear");
        criteriaModel.add("LT");
        criteriaModel.add("2001");
        var result = bookService.filterBooks(criteriaModel);
        assertEquals(1, result.size());
        assertEquals("Alice in Wonderland", result.get(0).getTitle());
    }

    @Test
    void filterBooksGE() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                2000, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book Alice 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var criteriaModel = new ArrayList<String>();
        criteriaModel.add("publicationYear");
        criteriaModel.add("GE");
        criteriaModel.add("2003");
        var result = bookService.filterBooks(criteriaModel);
        assertEquals(2, result.size());
        assertEquals("Book Alice 2", result.get(0).getTitle());
        assertEquals("Book 3", result.get(1).getTitle());
    }

    @Test
    void filterBooksGT() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                2000, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book Alice 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var criteriaModel = new ArrayList<String>();
        criteriaModel.add("publicationYear");
        criteriaModel.add("GT");
        criteriaModel.add("2003");
        var result = bookService.filterBooks(criteriaModel);
        assertEquals(1, result.size());
        assertEquals("Book Alice 2", result.get(0).getTitle());
    }

    @Test
    void filterBooksLike() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                2000, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var criteriaModel = new ArrayList<String>();
        criteriaModel.add("title");
        criteriaModel.add("LIKE");
        criteriaModel.add("lic");
        var result = bookService.filterBooks(criteriaModel);
        assertEquals(2, result.size());
        assertEquals("Alice in Wonderland", result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    @Test
    void filterBooksEQ() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                2000, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, "Book 3", "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var criteriaModel = new ArrayList<String>();
        criteriaModel.add("title");
        criteriaModel.add("EQ");
        criteriaModel.add("Book 2");
        var result = bookService.filterBooks(criteriaModel);
        assertEquals(1, result.size());
        assertEquals("Book 2", result.get(0).getTitle());
    }

    @Test
    void filterBooksNotNull() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, null, "Lewis Carroll",
                2000, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, null, "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var criteriaModel = new ArrayList<String>();
        criteriaModel.add("title");
        criteriaModel.add("NOT_NULL");
        criteriaModel.add(null);
        var result = bookService.filterBooks(criteriaModel);
        assertEquals(2, result.size());
        assertEquals("Book 2", result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    @Test
    void filterBooksNull() {
        userService.saveUser(userOne());
        bookService.saveBook(new BookEntity(1L, null, "Lewis Carroll",
                2000, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(3L, null, "Lewis Carroll",
                2003, 129, 10, "Книга в идеальном состоянии.", null), 1L);
        bookService.saveBook(new BookEntity(4L, "Book Alice 4", "Author",
                2001, 21, 3, "Книга в плохом состоянии.", null), 1L);
        var criteriaModel = new ArrayList<String>();
        criteriaModel.add("title");
        criteriaModel.add("NULL");
        criteriaModel.add(null);
        var result = bookService.filterBooks(criteriaModel);
        assertEquals(2, result.size());
        assertNull(result.get(0).getTitle());
        assertEquals(2000, result.get(0).getPublicationYear());
        assertNull(result.get(1).getTitle());
        assertEquals(2003, result.get(1).getPublicationYear());
    }

    private static UserEntity userOne() {
        return new UserEntity(1L, "Bob", "boB7523", "@BobTg", "Moscow",
                "Oktyabrsky", new ArrayList<>());
    }
}