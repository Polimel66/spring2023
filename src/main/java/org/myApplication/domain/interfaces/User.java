package org.myApplication.domain.interfaces;


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

//    /**
//     * Метод получения книг в библиотеке пользователя
//     *
//     * @return возвращает список книг из библиотеки пользователя
//     */
//    List<Book> getBooks(); в процессе работы, закомментировала тк пока не доделала и это не работает
//    List<Genres> getFavoriteGenres(); пока думаю как правильнее сделать, список любимых жанров
}
