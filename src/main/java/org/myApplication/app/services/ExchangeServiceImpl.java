package org.myApplication.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myApplication.app.entity.BookEntity;
import org.myApplication.app.entity.ExchangeEntity;
import org.myApplication.app.repositories.ExchangeRepository;
import org.myApplication.domain.enums.ExchangeState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ExchangeServiceImpl implements org.myApplication.app.interfaces.ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final BookServiceImpl bookService;
    private UserServiceImpl userService;

    @Override
    public void addExchange(Long requestingUserId, Long respondingUserId, Long requestedBookId) {
        var firstUser = userService.findUserById(requestingUserId).get();
        var secondUser = userService.findUserById(respondingUserId).get();
        var book = bookService.findBookById(requestedBookId).get();
        var exchangeEntity = new ExchangeEntity(1L, ExchangeState.NOT_PROCESSED, false, firstUser, secondUser, book);
        exchangeRepository.save(exchangeEntity);
        log.info("Новый обмен упешно создан");
    }

    @Override
    public ExchangeEntity changeExchange(ExchangeEntity changedExchange, Long exchangeId) {
        return exchangeRepository.findById(exchangeId).map(exchangeEntity -> {
            exchangeEntity.setExchangeState(changedExchange.getExchangeState());
            exchangeEntity.setExchangeMade(changedExchange.isExchangeMade());
            exchangeEntity.setRequestingUser(changedExchange.getRequestingUser());
            exchangeEntity.setRespondingUser(changedExchange.getRespondingUser());
            exchangeEntity.setRequestedBook(changedExchange.getRequestedBook());
            var result = exchangeRepository.save(exchangeEntity);
            log.info("Данные заданного обмена успешно изменены");
            return result;
        }).orElseThrow(() -> new RuntimeException("\nИзменяемый обмен не найден"));
    }

    @Override
    public void deleteExchange(Long exchangeId) {
        exchangeRepository.deleteById(exchangeId);
        log.info("Обмен успешно удален");
    }

    @Override
    public List<BookEntity> getSuggestedBooks(Long exchangeId) {
        var exchange = exchangeRepository.findById(exchangeId).get();
        var books = exchange.getRequestingUser().getBooks();
        log.info("Предложенные книги успешно найдены");
        return books;
    }

    @Override
    public List<String> acceptExchange(Long exchangeId) {
        var exchange = exchangeRepository.findById(exchangeId).get();
        if (exchange.getExchangeState() == ExchangeState.NOT_PROCESSED && !exchange.isExchangeMade()) {
            exchange.setExchangeState(ExchangeState.ADOPTED);
            var firstUserContact = exchange.getRequestingUser().getContact();
            var secondUserContact = exchange.getRespondingUser().getContact();
            changeExchange(exchange, exchangeId);
            var contacts = new ArrayList<String>();
            contacts.add(firstUserContact);
            contacts.add(secondUserContact);
            log.info("Обмен успешно принят, необходимые контактные данные найдены");
            return contacts;
        } else {
            log.info("Обмен уже был принят, отклонен или завершен");
            return new ArrayList<>();
        }
    }

    @Override
    public void rejectExchange(Long exchangeId) {
        var exchange = exchangeRepository.findById(exchangeId).get();
        if (exchange.getExchangeState() == ExchangeState.NOT_PROCESSED && !exchange.isExchangeMade()) {
            exchange.setExchangeState(ExchangeState.REJECTED);
            exchange.setExchangeMade(true);
            changeExchange(exchange, exchangeId);
            log.info("Обмен успешно отклонен");
        } else {
            log.info("Обмен уже был принят, отклонен или завершен");
        }
    }

    @Override
    public void performExchange(Long exchangeId, Long libraryBookId) {
        var exchange = exchangeRepository.findById(exchangeId).get();
        exchange.setExchangeMade(true);
        var firstUser = exchange.getRequestingUser();
        var secondUser = exchange.getRespondingUser();
        var libraryBook = bookService.findBookById(libraryBookId).get();
        var requestedBook = exchange.getRequestedBook();
        bookService.deleteBookById(requestedBook.getId());
        bookService.deleteBookById(libraryBook.getId());
        bookService.saveBook(requestedBook, firstUser.getId());
        bookService.saveBook(libraryBook, secondUser.getId());
        changeExchange(exchange, exchangeId);
        log.info("Обмен успешно завершен");
    }
}
