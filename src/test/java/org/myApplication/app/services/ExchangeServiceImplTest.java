package org.myApplication.app.services;

import org.junit.jupiter.api.Test;
import org.myApplication.app.repositories.ExchangeRepository;
import org.myApplication.domain.enums.ExchangeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ExchangeServiceImplTest {
    @Autowired
    ExchangeServiceImpl exchangeService;
    @Autowired
    ExchangeRepository exchangeRepository;

    @Test
    @Sql({"/users_data.sql", "/exchange_books_data.sql"})
    void addExchange() {
        exchangeService.addExchange(1L, 2L, 2L);
        var dbExchange = exchangeRepository.findById(1L).get();
        assertNotNull(dbExchange);
        assertEquals("TestNick321", dbExchange.getRequestingUser().getNickname());
    }

    @Test
    @Sql({"/users_data.sql", "/exchange_books_data.sql", "/exchange_data.sql"})
    void changeExchange() {
        var dbExchange = exchangeRepository.findById(1L).get();
        dbExchange.setExchangeState(ExchangeState.ADOPTED);
        var changedExchange = exchangeService.changeExchange(dbExchange, 1L);
        assertEquals(ExchangeState.ADOPTED, changedExchange.getExchangeState());
    }

    @Test
    @Sql({"/users_data.sql", "/exchange_books_data.sql", "/exchange_data.sql"})
    void deleteExchange() {
        exchangeService.deleteExchange(1L);
        assertEquals(Optional.empty(), exchangeRepository.findById(1L));
    }

    @Test
    @Sql({"/users_data.sql", "/exchange_books_data.sql", "/exchange_data.sql"})
    void getSuggestedBooks() {
        var suggestedBooks = exchangeService.getSuggestedBooks(1L);
        assertEquals(1, suggestedBooks.size());
        assertEquals("Alice in Wonderland", suggestedBooks.get(0).getTitle());
    }

    @Test
    @Sql({"/users_data.sql", "/exchange_books_data.sql", "/exchange_data.sql"})
    void acceptExchange() {
        exchangeService.acceptExchange(1L);
        var dbExchange = exchangeRepository.findById(1L).get();
        assertEquals(ExchangeState.ADOPTED, dbExchange.getExchangeState());
    }

    @Test
    @Sql({"/users_data.sql", "/exchange_books_data.sql", "/exchange_data.sql"})
    void rejectExchange() {
        exchangeService.rejectExchange(1L);
        var dbExchange = exchangeRepository.findById(1L).get();
        assertEquals(ExchangeState.REJECTED, dbExchange.getExchangeState());
    }
}