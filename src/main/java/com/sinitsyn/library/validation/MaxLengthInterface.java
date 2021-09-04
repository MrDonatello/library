package com.sinitsyn.library.validation;

import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.validation.validdators.MaxLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaxLengthValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLengthInterface {

    String message() default "Invalid max length";

    ErrorCode error() default ErrorCode.INVALID_MAX_LENGTH;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
