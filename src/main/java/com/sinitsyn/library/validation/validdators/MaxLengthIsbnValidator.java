package com.sinitsyn.library.validation.validdators;

import com.sinitsyn.library.validation.MaxLengthIsbnInterface;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxLengthIsbnValidator implements ConstraintValidator<MaxLengthIsbnInterface, String> {

    @Value("${max_isbn_length}")
    private int maxIsbnLength;

    @Override
    public void initialize(MaxLengthIsbnInterface maxLengthInterface) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() < maxIsbnLength;
    }
}
