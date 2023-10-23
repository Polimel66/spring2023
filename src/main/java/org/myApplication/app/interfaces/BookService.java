package org.myApplication.app.interfaces;

import org.myApplication.app.Book;
import org.myApplication.app.User;

import java.util.Optional;

public interface BookService {
    /**
     * Метод сохраняет новую книгу в базу данных
     *
     * @param book новая книга
     * @return возвращает сохраненную книгу
     */
    Book saveBook(Book book);

    /**
     * Метод удаляет книгу по id
     *
     * @param id идентификатор удаляемой книги
     */
    void deleteBookById(Long id);

    /**
     * Метод получения книги по id
     *
     * @param id идентификатор книги, которую необходимо найти
     * @return возвращает полученную по id книгу
     */
    Optional<Book> findBookById(Long id);

    /**
     * Метод изменения данных книги в базе данных
     *
     * @param changedBook книга с измененными данными
     * @return возвращает измененную книгу
     */
    User changeBook(Book changedBook);
}
