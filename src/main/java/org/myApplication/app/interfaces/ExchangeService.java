package org.myApplication.app.interfaces;

import org.myApplication.app.entity.BookEntity;
import org.myApplication.app.entity.ExchangeEntity;

import java.util.List;

public interface ExchangeService {
    /**
     * Метод создания и сохранения запроса на обмен
     *
     * @param requestingUserId запрашивающий обмен пользователь
     * @param respondingUserId пользователь, которому направлен запрос
     * @param requestedBookId  запрашиваемая книга
     */
    void addExchange(Long requestingUserId, Long respondingUserId, Long requestedBookId);

    /**
     * Метод изменения данных обмена в бд
     *
     * @param changedExchange измененный обмен
     * @param exchangeId      идентификатор изменяемого обмена
     * @return измененный обмен
     */
    ExchangeEntity changeExchange(ExchangeEntity changedExchange, Long exchangeId);

    /**
     * Метод удаления обмена из бд
     *
     * @param exchangeId идентификатор удаляемого обмена
     */
    void deleteExchange(Long exchangeId);

    /**
     * Метод получения книг запрашивающего обмен пользователя
     *
     * @param exchangeId идентификатор обмена
     * @return возвращает список книг
     */
    List<BookEntity> getSuggestedBooks(Long exchangeId);

    /**
     * Метод принятия обмена
     *
     * @param exchangeId идентификатор обмена
     * @return возвращает список контактных данных, участвующих в обмене пользователей
     */
    List<String> acceptExchange(Long exchangeId);

    /**
     * Метод отклонения обмена
     *
     * @param exchangeId идентификатор обмена
     */
    void rejectExchange(Long exchangeId);

    /**
     * Метод произведения обмена
     *
     * @param exchangeId    идентификатор обмена
     * @param libraryBookId идентификатор книги, выбранной отвечающим пользователем из библиотеки запрашивающего
     */
    void performExchange(Long exchangeId, Long libraryBookId);
}
