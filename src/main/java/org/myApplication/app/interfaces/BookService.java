package org.myApplication.app.interfaces;

import org.myApplication.app.entity.BookEntity;

import org.myApplication.app.filter.CriteriaModel;
import org.springframework.data.domain.Pageable;

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
     * @param pageable параметры пагинации
     * @return возвращает книги из бд по заданным параметрам
     */
    List<BookEntity> findAllBooks(Pageable pageable);

    /**
     * Метод поиска книг с фильтрацией и сортировкой
     *
     * @param pageable параметры пагинации
     * @param filters  фильтры
     * @return возвращает список необходимых отфильтрованных и отсортированных по заданным параметрам книг
     */
    List<BookEntity> searchBooks(Pageable pageable, List<CriteriaModel> filters);

    /**
     * Метод получения отфильтрованных книг
     *
     * @param filters фильтры
     * @return список отфильтрованных книг
     */
    List<BookEntity> filterBooks(List<CriteriaModel> filters);
}
