package org.myApplication.domain.interfaces;


public interface Book { //интерфейс основного класса, реализующего понятие книга
    String getTitle(); // возвращает название книги

    String getAuthor(); // возвращает автора

    int getPublicationYear(); // возвращает год публикации

    int getPagesNumber(); // возвращает количество страниц

    int getBookCondition(); // возвращает состояние книги по 10 бальной системе

    String getTextBookCondition(); // возвращает текст описывающий словестно состояние книги
//    List<Image> getPhotos(); возвращает список изображений, пока думаю как правильнее сделать
}
