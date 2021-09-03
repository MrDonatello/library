package com.sinitsyn.library.validation.validdators;

import com.sinitsyn.library.validation.MaxNameLength;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MaxNameLengthValidator implements ConstraintValidator<MaxNameLength, String> {

    @Value("${max_name_length}")
    private int maxNameLength;

    @Override
    public void initialize(MaxNameLength maxNameLength) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() < maxNameLength;
    }
}
