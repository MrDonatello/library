package com.sinitsyn.library.validation.validdators;

import com.sinitsyn.library.validation.MaxLengthInterface;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MaxLengthValidator implements ConstraintValidator<MaxLengthInterface, String> {

    @Value("${max_isbn_length}")
    private int maxNameLength;

    @Override
    public void initialize(MaxLengthInterface maxLengthInterface) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() < maxNameLength;
    }
}
