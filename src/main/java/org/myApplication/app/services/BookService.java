package org.myApplication.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myApplication.app.Book;
import org.myApplication.app.repositories.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BookService implements org.myApplication.app.interfaces.BookService {

    private final BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        log.info("Новая книга успешно сохранена");
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
        log.info("Книга успешно удалена");
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        log.info("Книга с id - {} успешно найдена", id);
        return bookRepository.findById(id);
    }

    @Override
    public Book changeBook(Book changedBook) {
        log.info("Данные заданной книги успешно изменены");
        return bookRepository.save(changedBook);
    }

    @Override
    public List<Book> findAllBooks(PageRequest pageRequest) {
        log.info("Все нужные книги с заданной страницы найдены");
        return bookRepository.findAllBooksByPageRequest(pageRequest);
    }
}
