package org.myApplication.domain.interfaces;

public interface User { //интерфейс основного класса, реализующего понятие пользователь
    String getNickname(); // возвращает никнейм

    String getPassword(); // возвращает пароль

    String getContact(); // возвращает контакт

    String getCity(); // возвращает город

    String getDistrict(); // возвращает район
//    List<Genres> getFavoriteGenres(); пока думаю как правильнее сделать, список любимых жанров
}
