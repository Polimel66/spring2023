package org.myApplication.domain.interfaces;

import org.myApplication.domain.enums.ExchangeState;

/**
 * Интерфейс основного класса, реализующего понятие обмен
 *
 * @autor Мельникова Полина
 */
public interface Exchange {
    /**
     * Метод получения состояния обмена
     *
     * @return состояние обмена
     */
    ExchangeState getExchangeState();

    /**
     * Метод получения информации, закончен ли обмен
     *
     * @return закончен обмен или нет
     */
    boolean isExchangeMade();

    /**
     * Запрашивающий обмен пользователь
     *
     * @return возвращает пользователя
     */
    User getRequestingUser();

    /**
     * Пользователь, которому направлен обмен
     *
     * @return возвращает пользователя
     */
    User getRespondingUser();

    /**
     * Запрашиваемая книга
     *
     * @return возвращает книгу
     */
    Book getRequestedBook();
}
