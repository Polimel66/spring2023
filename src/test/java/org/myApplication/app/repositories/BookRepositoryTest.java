package org.myApplication.app.repositories;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAllBooksByPageRequest() {
        var user = new UserEntity(1L, "Bob", "boB7523", "@BobTg", "Moscow",
                "Oktyabrsky", new ArrayList<>());
        userRepository.save(user);
        bookRepository.save(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        bookRepository.save(new BookEntity(2L, "Twilight", "Stephanie Mayer",
                2001, 123, 8, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        bookRepository.save(new BookEntity(3L, "Cipollino", "Gianni Rodari",
                1996, 78, 10, "Книга в идеальном состоянии.", user));
        var resultSearchBooks = bookRepository.findAllBooksByPageRequest(PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "publicationYear")));
        assertEquals(2, resultSearchBooks.size());
        assertEquals("Alice in Wonderland", resultSearchBooks.get(0).getTitle());
        assertEquals("Cipollino", resultSearchBooks.get(1).getTitle());
    }

    @Test
    void findByTitleOrAuthorByPageRequest() {
        var user = new UserEntity(1L, "Bob", "boB7523", "@BobTg", "Moscow",
                "Oktyabrsky", new ArrayList<>());
        userRepository.save(user);
        bookRepository.save(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1865, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        bookRepository.save(new BookEntity(2L, "Cipollino", "Gianni Rodari",
                1996, 78, 10, "Книга в идеальном состоянии.", user));
        bookRepository.save(new BookEntity(3L, "Twilight", "Alice Mayer",
                2001, 123, 8, "Книга в хорошем состоянии, но" +
                " есть потертости.", user));
        bookRepository.save(new BookEntity(4L, "Harry Potter", "J. K. Rowling",
                1986, 556, 10, "Книга в идеальном состоянии.", user));
        bookRepository.save(new BookEntity(5L, "Winnie the Pooh", "Alan Milne",
                1926, 235, 10, "Книга в идеальном состоянии.", user));
        var resultSearchBooks = bookRepository.findByTitleOrAuthorByPageRequest("%Al%", "%Al%",
                PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "publicationYear")));
        assertEquals(2, resultSearchBooks.size());
        assertEquals("Alice in Wonderland", resultSearchBooks.get(0).getTitle());
        assertEquals("Winnie the Pooh", resultSearchBooks.get(1).getTitle());
    }
}