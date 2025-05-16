package com.bobbysoft.application.usermanagement.service;

import com.bobbysoft.application.usermanagement.dto.UserRegistrationDto;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.UUID;

@BrowserCallable
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserRegistrationService {

    @Nonnull
    @AnonymousAllowed
    public UserRegistrationDto registerUser(UserRegistrationDto registration) {
        return registration;
    }

    public void deleteUser(UUID uuid) {
    }
}
