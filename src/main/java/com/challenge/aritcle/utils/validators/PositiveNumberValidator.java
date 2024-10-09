package com.challenge.aritcle.utils.validators;

import com.challenge.aritcle.exceptionHanller.CustomRunTimeException;
import com.challenge.aritcle.exceptionHanller.ErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class PositiveNumberValidator implements ConstraintValidator<PositiveNumber,String> {
    @Override
    public void initialize(PositiveNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!isNumeric(value)) {
            throw  new CustomRunTimeException(ErrorCode.PAGE_SIZE_POSITIVE);
        }
        return true;
    }
}
