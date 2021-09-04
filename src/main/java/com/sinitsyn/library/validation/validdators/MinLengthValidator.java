package com.sinitsyn.library.validation.validdators;

import com.sinitsyn.library.validation.MinLengthInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinLengthValidator implements ConstraintValidator<MinLengthInterface, Integer> {

    @Override
    public void initialize(MinLengthInterface constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer i, ConstraintValidatorContext constraintValidatorContext) {

        return i >= 1;
    }
}
