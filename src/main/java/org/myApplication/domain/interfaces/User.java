package org.myApplication.domain.interfaces;

import java.util.List;

/**
 * Интерфейс основного класса, реализующего понятие пользователь
 *
 * @autor Мельникова Полина
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
//    List<Genres> getFavoriteGenres(); пока думаю как правильнее сделать, список любимых жанров
}
