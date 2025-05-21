package com.bobbysoft.application.usermanagement.service;

import com.bobbysoft.application.usermanagement.dto.UserInfoDto;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@AnonymousAllowed
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserInfoService {
    private static final String LOGOUT_SUCCESS_URL = "/";

    @RolesAllowed("USER")
    public boolean isUser() {
        return true;
    }

    @Nonnull
    @AnonymousAllowed
    public UserInfoDto getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        final List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new UserInfoDto(auth.getName(), authorities, "", "");
    }
}
