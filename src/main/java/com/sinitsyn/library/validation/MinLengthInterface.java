package com.sinitsyn.library.validation;

import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.validation.validdators.MinLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinLengthValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLengthInterface {

    String message();

    ErrorCode error();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
