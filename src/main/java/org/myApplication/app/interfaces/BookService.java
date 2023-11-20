package org.myApplication.app.interfaces;

import org.myApplication.app.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    /**
     * Метод сохранения новой книги
     *
     * @param book   новая книга
     * @param userId идентификатор пользователя, сохраняющего книгу
     * @return возвращает сохраненную книгу
     */
    BookEntity saveBook(BookEntity book, Long userId);

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
    Optional<BookEntity> findBookById(Long id);

    /**
     * Метод изменения данных книги в базе данных
     *
     * @param changedBook измененная книга
     * @param userId      идентификатор изменяемой книги
     * @return возвращает измененную книгу
     */
    BookEntity changeBook(BookEntity changedBook, Long userId);

    /**
     * Метод постраничного получения книг из базы данных
     *
     * @param page             номер страницы
     * @param numberEntries    количество записей
     * @param sortingParameter параметр сортировки
     * @param sortingDirection направление сортировки
     * @return возвращает книги из бд по заданным параметрам
     */
    List<BookEntity> findAllBooks(int page, int numberEntries, String sortingParameter, String sortingDirection);

    /**
     * Метод поиска книг по названию и/или автору, совпадающих
     * или содержащих введенную строку
     *
     * @param searchStr        поисковая строка
     * @param page             необходимая страница
     * @param numberEntries    необходимое количество записей
     * @param sortingParameter параметр сортировки
     * @param sortingDirection направление сортировки
     * @return список книг, соответствующих поисковому запросу
     */
    List<BookEntity> searchBooks(String searchStr, int page, int numberEntries, String sortingParameter, String sortingDirection);
}
