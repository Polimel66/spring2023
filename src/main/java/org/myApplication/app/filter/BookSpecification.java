package org.myApplication.app.filter;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.*;
import org.myApplication.app.entity.BookEntity;
import org.myApplication.domain.enums.Operation;
import org.springframework.data.jpa.domain.Specification;

import java.util.EnumSet;

public class BookSpecification implements Specification<BookEntity> {
    private static final EnumSet<Operation> NULL_OPERATIONS = EnumSet.of(Operation.NULL, Operation.NOT_NULL);
    private final CriteriaModel criteriaModel;

    public BookSpecification(CriteriaModel criteriaModel) {
        checkCriteria(criteriaModel);
        this.criteriaModel = criteriaModel;
    }

    /**
     * Метод формирования предиката для фильтрации
     *
     * @param root            данные для построителя JPQL-выражений
     * @param query           общие характеристики запроса
     * @param criteriaBuilder построитель JPQL-выражений
     * @return предикат
     * (тестирование метода проводится вместе с тестированием метода filterBooks изи сервиса)
     */
    @Override
    public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        checkCriteria(criteriaModel);
        Operation operation = criteriaModel.getOperation();
        Path<Object> expression = root.get(criteriaModel.getField());
        String value = criteriaModel.getValue();
        switch (operation) {
            case NULL -> {
                return criteriaBuilder.isNull(expression);
            }
            case NOT_NULL -> {
                return criteriaBuilder.isNotNull(expression);
            }
            case EQ -> {
                return criteriaBuilder.equal(expression, value);
            }
            case LIKE -> {
                String str = "%" + value + "%";
                return criteriaBuilder.like(expression.as(String.class), str);
            }
            case GT -> {
                return criteriaBuilder.gt(expression.as(Integer.class), Integer.parseInt(value));
            }
            case LT -> {
                return criteriaBuilder.lt(expression.as(Integer.class), Integer.parseInt(value));
            }
            case GE -> {
                return criteriaBuilder.ge(expression.as(Integer.class), Integer.parseInt(value));
            }
            case LE -> {
                return criteriaBuilder.le(expression.as(Integer.class), Integer.parseInt(value));
            }
        }
        return null;
    }

    private void checkCriteria(CriteriaModel criteriaModel) {
        if (criteriaModel == null) {
            throw new IllegalArgumentException("CriteriaModel is null");
        }
        if (StringUtils.isBlank(criteriaModel.getField())) {
            throw new IllegalArgumentException("Field must be not null!");
        }
        Operation operation = criteriaModel.getOperation();
        if (operation == null) {
            throw new IllegalArgumentException("Operation must be not null!");
        }
        if (!NULL_OPERATIONS.contains(operation) && criteriaModel.getValue() == null) {
            throw new IllegalArgumentException("Value must be not null!");
        }
    }
}
