package com.kat.orderlogisticsystem1.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class StringArrayValidator implements ConstraintValidator<ValidStringArray, Object[]>
{
    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        for (Object str : value) {
            if (!(str instanceof String)) {
                return false;
            }
        }
        return true;
    }
}