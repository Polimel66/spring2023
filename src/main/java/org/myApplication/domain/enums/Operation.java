package org.myApplication.domain.enums;

/**
 * Enum из операций фильтрации
 *
 * @autor Мельникова Полина
 */
public enum Operation {
    /**
     * Пустой объект
     */
    NULL,
    /**
     * Непустой объект
     */
    NOT_NULL,
    /**
     * Равенство
     */
    EQ,
    /**
     * Поиск по шаблону
     */
    LIKE,
    /**
     * Строго больше
     */
    GT,
    /**
     * Больше либо равно
     */
    GE,
    /**
     * Строго меньше
     */
    LT,
    /**
     * Меньше либо равно
     */
    LE
}