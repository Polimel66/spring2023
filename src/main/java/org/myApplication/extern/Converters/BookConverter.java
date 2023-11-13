package org.myApplication.extern.Converters;

import org.myApplication.app.Entities.BookEntity;
import org.myApplication.extern.Models.BookModel;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    public BookEntity toEntity(BookModel bookModel) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookModel.getTitle());
        bookEntity.setAuthor(bookModel.getAuthor());
        bookEntity.setPublicationYear(bookModel.getPublicationYear());
        bookEntity.setPagesNumber(bookModel.getPagesNumber());
        bookEntity.setBookCondition(bookModel.getBookCondition());
        bookEntity.setTextBookCondition(bookModel.getTextBookCondition());
        return bookEntity;
    }

    public BookModel toModel(BookEntity bookEntity) {
        BookModel bookModel = new BookModel();
        bookModel.setTitle(bookEntity.getTitle());
        bookModel.setAuthor(bookEntity.getAuthor());
        bookModel.setPublicationYear(bookEntity.getPublicationYear());
        bookModel.setBookCondition(bookEntity.getBookCondition());
        bookModel.setTextBookCondition(bookEntity.getTextBookCondition());
        return bookModel;
    }
}
