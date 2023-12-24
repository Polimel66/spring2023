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
import org.springframework.test.context.jdbc.Sql;

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
    @Sql({"/users_data.sql", "/books_data.sql"})
    void findAllBooksByPageRequest() {
        var resultSearchBooks = bookRepository.findAllBooksByPageRequest(PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "publicationYear")));
        assertEquals(2, resultSearchBooks.size());
        assertNull(resultSearchBooks.get(0).getTitle());
        assertEquals("Book Alice 4", resultSearchBooks.get(1).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/books_data.sql"})
    void findByTitleOrAuthorByPageRequest() {
        var resultSearchBooks = bookRepository.findByTitleOrAuthorByPageRequest("%Al%", "%Al%",
                PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "publicationYear")));
        assertEquals(2, resultSearchBooks.size());
        assertNull(resultSearchBooks.get(0).getTitle());
        assertEquals("Book Alice 4", resultSearchBooks.get(1).getTitle());
    }
}