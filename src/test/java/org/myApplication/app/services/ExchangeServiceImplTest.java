package org.myApplication.app.services;

import org.junit.jupiter.api.Test;
import org.myApplication.app.entity.BookEntity;
import org.myApplication.app.entity.UserEntity;
import org.myApplication.app.repositories.ExchangeRepository;
import org.myApplication.domain.enums.ExchangeState;
import org.myApplication.domain.enums.Genres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ExchangeServiceImplTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    ExchangeServiceImpl exchangeService;
    @Autowired
    ExchangeRepository exchangeRepository;

    @Test
    void addExchange() {
        makeExchangeData();
        exchangeService.addExchange(1L, 2L, 2L);
        var dbExchange = exchangeRepository.findById(1L).get();
        assertNotNull(dbExchange);
        assertEquals("Bob", dbExchange.getRequestingUser().getNickname());
    }

    @Test
    void changeExchange() {
        makeExchangeData();
        exchangeService.addExchange(1L, 2L, 2L);
        var dbExchange = exchangeRepository.findById(1L).get();
        dbExchange.setExchangeState(ExchangeState.ADOPTED);
        var changedExchange = exchangeService.changeExchange(dbExchange, 1L);
        assertEquals(ExchangeState.ADOPTED, changedExchange.getExchangeState());
    }

    @Test
    void deleteExchange() {
        makeExchangeData();
        exchangeService.addExchange(1L, 2L, 2L);
        exchangeService.deleteExchange(1L);
        assertEquals(Optional.empty(), exchangeRepository.findById(1L));
    }

    @Test
    void getSuggestedBooks() {
        makeExchangeData();
        exchangeService.addExchange(1L, 2L, 2L);
        var suggestedBooks = exchangeService.getSuggestedBooks(1L);
        assertEquals(1, suggestedBooks.size());
        assertEquals("Alice in Wonderland", suggestedBooks.get(0).getTitle());
    }

    @Test
    void acceptExchange() {
        makeExchangeData();
        exchangeService.addExchange(1L, 2L, 2L);
        exchangeService.acceptExchange(1L);
        var dbExchange = exchangeRepository.findById(1L).get();
        assertEquals(ExchangeState.ADOPTED, dbExchange.getExchangeState());
    }

    @Test
    void rejectExchange() {
        makeExchangeData();
        exchangeService.addExchange(1L, 2L, 2L);
        exchangeService.rejectExchange(1L);
        var dbExchange = exchangeRepository.findById(1L).get();
        assertEquals(ExchangeState.REJECTED, dbExchange.getExchangeState());
    }

    private void makeExchangeData() {
        userService.saveUser(new UserEntity(1L, "Bob", "boB7523", "@BobTg", "Moscow", "Oktyabrsky", List.of(Genres.DETECTIVE), new ArrayList<>()));
        userService.saveUser(new UserEntity(2L, "Den", "den7523", "@DenTg", "Moscow", "Oktyabrsky", List.of(Genres.DETECTIVE), new ArrayList<>()));
        bookService.saveBook(new BookEntity(1L, "Alice in Wonderland", "Lewis Carroll",
                1998, 156, 7, "Книга в хорошем состоянии, но" +
                " есть потертости.", null), 1L);
        bookService.saveBook(new BookEntity(2L, "Book 2", "Lewis Carroll",
                2005, 21, 3, "Книга в плохом состоянии.", null), 2L);
    }
}