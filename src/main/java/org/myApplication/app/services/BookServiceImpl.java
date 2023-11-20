package org.myApplication.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.myApplication.app.entity.BookEntity;
import org.myApplication.app.repositories.BookRepository;
import org.myApplication.app.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BookServiceImpl implements org.myApplication.app.interfaces.BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public BookEntity saveBook(BookEntity book, Long userId) {
        var user = userRepository.findById(userId).get();
        user.addBook(book);
        userRepository.save(user);
        log.info("Новая книга успешно сохранена");
        return book;
    }

    @Override
    public void deleteBookById(Long id) {
        var book = bookRepository.findById(id).get();
        var user = book.getBookOwner();
        user.removeBook(book);
        userRepository.save(user);
        log.info("Книга успешно удалена");
    }

    @Override
    public Optional<BookEntity> findBookById(Long id) {
        var result = bookRepository.findById(id);
        log.info("Книга с id - {} успешно найдена", id);
        return result;
    }

    @Override
    public BookEntity changeBook(BookEntity changedBook, Long bookId) {

        return bookRepository.findById(bookId).map(bookEntity -> {
            bookEntity.setTitle(changedBook.getTitle());
            bookEntity.setAuthor(changedBook.getAuthor());
            bookEntity.setPublicationYear(changedBook.getPublicationYear());
            bookEntity.setPagesNumber(changedBook.getPagesNumber());
            bookEntity.setBookCondition(changedBook.getBookCondition());
            bookEntity.setTextBookCondition(changedBook.getTextBookCondition());
            var result = bookRepository.save(bookEntity);
            log.info("Данные заданной книги успешно изменены");
            return result;
        }).orElseThrow(() -> new RuntimeException("\nКнига не найдена"));
    }

    @Override
    public List<BookEntity> findAllBooks(int page, int numberEntries, String sortingParameter, String sortingDirection) {
        var result = bookRepository.findAllBooksByPageRequest(PageRequest.of(page, numberEntries,
                Sort.by(Sort.Direction.valueOf(sortingDirection), sortingParameter)));
        log.info("Все нужные книги с заданной страницы найдены");
        return result;
    }

    @Override
    public List<BookEntity> searchBooks(String str, int page, int numberEntries, String sortingParameter, String sortingDirection) {
        var searchStr = "%" + str + "%";
        var result = bookRepository.findByTitleOrAuthorByPageRequest(searchStr, searchStr,
                PageRequest.of(page, numberEntries, Sort.by(Sort.Direction.valueOf(sortingDirection),
                        sortingParameter)));
        log.info("Книги по данному запросу найдены");
        return result;
    }
}
