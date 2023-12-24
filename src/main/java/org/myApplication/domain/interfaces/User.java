package org.myApplication.domain.interfaces;


import org.myApplication.domain.enums.Genres;

import java.util.List;

/**
 * Пользователь
 *
 * @author Мельникова Полина
 */
public interface User {
    /**
     * Метод получения никнейма пользователя
     *
     * @return возвращает никнейм
     */
    String getNickname();

    /**
     * Метод получения пароля пользователя
     *
     * @return возвращает пароль
     */
    String getPassword();

    /**
     * Метод получения контактных данных пользователя
     *
     * @return возвращает контактные данные
     */
    String getContact();

    /**
     * Метод получения города проживания пользователя
     *
     * @return возвращает город проживания
     */
    String getCity();

    /**
     * Метод получения района проживания пользователя
     *
     * @return возвращает район проживания
     */
    String getDistrict();

    /**
     * Метод удаления книги через пользователя.
     * Метод необходим для решения проблем Hibernate
     * при двусторонних связях.
     *
     * @param book книга, которую необходимо удалить
     */
    void removeBook(Book book);

    /**
     * Метод добавления книги через пользователя.
     * Метод необходим для решения проблем Hibernate
     * при двусторонних связях.
     *
     * @param book добавляемая книга
     */
    void addBook(Book book);

    /**
     * Метод получения любимых жанров пользователя
     *
     * @return список жанров
     */
    List<Genres> getFavoriteGenres();
}
