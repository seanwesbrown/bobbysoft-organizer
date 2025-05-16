package com.bobbysoft.application.usermanagement.dto.validator;

import com.bobbysoft.application.usermanagement.dto.UserRegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConfirmationValidator implements ConstraintValidator<ValidPasswordConfirmation, UserRegistrationDto> {
    @Override
    public void initialize(ValidPasswordConfirmation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto, ConstraintValidatorContext constraintValidatorContext) {
        String password = userRegistrationDto.password();
        String confirmation = userRegistrationDto.passwordConfirmation();

        return password.equals(confirmation);
    }
}
