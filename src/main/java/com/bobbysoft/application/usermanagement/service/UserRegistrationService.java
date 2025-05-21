package com.bobbysoft.application.usermanagement.service;

import com.bobbysoft.application.usermanagement.domain.AuthorityEntity;
import com.bobbysoft.application.usermanagement.domain.UserEntity;
import com.bobbysoft.application.usermanagement.domain.UserRepository;
import com.bobbysoft.application.usermanagement.dto.UserRegistrationDto;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

@BrowserCallable
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserRegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Nonnull
    @AnonymousAllowed
    public UserRegistrationDto registerUser(UserRegistrationDto registration) {
        UserEntity user = new UserEntity();
        user.setUsername(registration.username());
        user.setPassword(passwordEncoder.encode(registration.password()));
        user.setEnabled(true);

        List<AuthorityEntity> authorities = new LinkedList<>();
        authorities.add(new AuthorityEntity(user, "ROLE_USER"));

        user.setAuthorities(authorities);

        userRepository.save(user);

        return registration;
    }
}
