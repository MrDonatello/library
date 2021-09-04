package com.sinitsyn.library.validation;

import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.validation.validdators.MaxLengthIsbnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaxLengthIsbnValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLengthIsbnInterface {

    String message() default "Invalid max length isbn";

    ErrorCode error() default ErrorCode.INVALID_MAX_LENGTH;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
