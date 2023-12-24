package org.myApplication.app.services;

import org.junit.jupiter.api.Test;
import org.myApplication.app.entity.BookEntity;
import org.myApplication.app.entity.UserEntity;
import org.myApplication.app.filter.CriteriaModel;
import org.myApplication.domain.enums.Genres;
import org.myApplication.domain.enums.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookServiceTest {
    @Autowired
    private BookServiceImpl bookService;

    @Test
    @Sql({"/users_data.sql"})
    void saveBook() {
        BookEntity dbBook = bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        assertNotNull(dbBook);
        assertEquals("Alice in Wonderland", dbBook.getTitle());
    }


    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void deleteBookById() {
        bookService.deleteBookById(1L);
        var dbBook = bookService.findBookById(1L);
        assertEquals(Optional.empty(), dbBook);
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void findBookById() {
        var dbBook = bookService.findBookById(2L);
        assertNotNull(dbBook);
        assertEquals("Book Alice 2", dbBook.get().getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void changeBook() {
        bookService.changeBook(new BookEntity(1L, "Измененное: название - Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        assertEquals("Измененное: название - Alice in Wonderland", bookService.findBookById(1L).get().getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void findAllBooks() {
        var result = bookService.findAllBooks(PageRequest.of(0, 2, Sort.by("publicationYear").ascending()));
        assertEquals(2, result.size());
        assertNull(result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void searchBooks() {
        var result = bookService.searchBooks(PageRequest.of(0, 2, Sort.by("publicationYear").ascending()),
                List.of(new CriteriaModel("title", Operation.LIKE, "Alic"), new CriteriaModel("publicationYear", Operation.GE, "2001")));
        assertEquals(2, result.size());
        assertEquals("Book Alice 4", result.get(0).getTitle());
        assertEquals("Book Alice 2", result.get(1).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooksLE() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("publicationYear", Operation.LE, "2001")));
        assertEquals(2, result.size());
        assertNull(result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooksLT() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("publicationYear", Operation.LT, "2001")));
        assertEquals(1, result.size());
        assertNull(result.get(0).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooksGE() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("publicationYear", Operation.GE, "2003")));
        assertEquals(2, result.size());
        assertEquals("Book Alice 2", result.get(0).getTitle());
        assertNull(result.get(1).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooksGT() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("publicationYear", Operation.GT, "2003")));
        assertEquals(1, result.size());
        assertEquals("Book Alice 2", result.get(0).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooksLike() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("title", Operation.LIKE, "lic")));
        assertEquals(2, result.size());
        assertEquals("Book Alice 2", result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooksEQ() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("title", Operation.EQ, "Book Alice 2")));
        assertEquals(1, result.size());
        assertEquals("Book Alice 2", result.get(0).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooksNotNull() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("title", Operation.NOT_NULL, null)));
        assertEquals(2, result.size());
        assertEquals("Book Alice 2", result.get(0).getTitle());
        assertEquals("Book Alice 4", result.get(1).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooksNull() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("title", Operation.NULL, null)));
        assertEquals(2, result.size());
        assertNull(result.get(0).getTitle());
        assertEquals(2000, result.get(0).getPublicationYear());
        assertNull(result.get(1).getTitle());
        assertEquals(2003, result.get(1).getPublicationYear());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void filterBooks() {
        var result = bookService.filterBooks(List.of(new CriteriaModel("title", Operation.LIKE, "Alic"),
                new CriteriaModel("bookCondition", Operation.GE, "7"), new CriteriaModel("publicationYear", Operation.LE, "2001")));
        assertEquals(1, result.size());
        assertEquals("Book Alice 4", result.get(0).getTitle());
    }
}