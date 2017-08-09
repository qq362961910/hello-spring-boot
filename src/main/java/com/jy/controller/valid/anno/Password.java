package com.jy.controller.valid.anno;

import com.jy.controller.valid.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {PasswordValidator.class}
)
public @interface Password {

    String message() default "PASSWORD_ERROR";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
