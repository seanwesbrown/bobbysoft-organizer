package com.bobbysoft.application.usermanagement.dto;

import com.bobbysoft.application.usermanagement.dto.validator.ValidPasswordConfirmation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ValidPasswordConfirmation
public record UserRegistrationDto(String username, String email, String password, String passwordConfirmation) {
    @NotNull(message = "Please enter a user name")
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 5, message = "Please enter a username at least 5 characters long")
    public String username() {
        return username;
    }

    @NotNull(message = "Please enter an email")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email address")
    public String email() {
        return email;
    }

    @NotNull(message = "Please enter a password")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 7, message = "Please enter a password at least 7 characters long")
    public String password() {
        return password;
    }

    @NotNull(message = "Please confirm your password")
    @NotBlank(message = "Please confirm your password")
    public String passwordConfirmation() {
        return passwordConfirmation;
    }
}
