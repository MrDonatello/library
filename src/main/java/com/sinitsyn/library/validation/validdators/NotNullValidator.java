package com.sinitsyn.library.validation.validdators;

import com.sinitsyn.library.validation.NotNullInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullValidator implements ConstraintValidator<NotNullInterface, Object> {


    @Override
    public void initialize(NotNullInterface constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return o != null;
    }
}
