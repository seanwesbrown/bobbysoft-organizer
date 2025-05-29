package com.bobbysoft.application.usermanagement.service;

import com.bobbysoft.application.usermanagement.domain.AuthorityEntity;
import com.bobbysoft.application.usermanagement.domain.UserEntity;
import com.bobbysoft.application.usermanagement.domain.UserRepository;
import com.bobbysoft.application.usermanagement.dto.UserRegistrationDto;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.security.VaadinAwareSecurityContextHolderStrategy;
import com.vaadin.hilla.BrowserCallable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    @Nonnull
    @AnonymousAllowed
    public UserRegistrationDto registerUser(UserRegistrationDto registration) {
        if (userRepository.findByUsername(registration.username()).isPresent()) {
            return registration.withError("The requested user name is already in use");
        }

        if (userRepository.findByEmail(registration.email()).isPresent()) {
            return registration.withError("The requested email is already in use");
        }

        userRepository.save(buildUser(registration));
        authenticateNewUser(registration.username(), registration.password());

        return registration.withError("");
    }

    private UserEntity buildUser(UserRegistrationDto registration) {
        UserEntity user = new UserEntity();
        user.setUsername(registration.username());
        user.setPassword(passwordEncoder.encode(registration.password()));
        user.setEmail(registration.email());
        user.setEnabled(true);

        List<AuthorityEntity> authorities = new LinkedList<>();
        authorities.add(new AuthorityEntity(user, "ROLE_USER"));

        user.setAuthorities(authorities);

        return user;
    }

    private void authenticateNewUser(String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authToken);

        VaadinAwareSecurityContextHolderStrategy strategy = new VaadinAwareSecurityContextHolderStrategy();

        SecurityContext context = strategy.createEmptyContext();
        context.setAuthentication(authentication);
        strategy.setContext(context);

        securityContextRepository.saveContext(context, request, response);
    }
}
