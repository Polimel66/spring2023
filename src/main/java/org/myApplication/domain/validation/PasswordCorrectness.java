package org.myApplication.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Ограничение для пароля
 */
@Constraint(validatedBy = PasswordCorrectnessConstraint.class)
@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface PasswordCorrectness {
    String message() default "Пароль невалиден";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
