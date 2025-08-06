package com.bobbysoft.application.usermanagement.entity;


import com.bobbysoft.application.modulemanagement.entity.ModuleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    public static final int USER_MAX_LENGTH = 50;
    public static final int EMAIL_MAX_LENGTH = 100;
    public static final int AVATAR_URL_MAX_LENGTH = 200;
    public static final int ENCODED_PASSWORD_MAX_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "username", nullable = false)
    @Size(max = USER_MAX_LENGTH)
    private String username;

    @Column(name = "email")
    @Size(max = EMAIL_MAX_LENGTH)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(max = ENCODED_PASSWORD_MAX_LENGTH)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "avatar_url")
    @Size(max = AVATAR_URL_MAX_LENGTH)
    private String avatarUrl;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Collection<AuthorityEntity> authorities;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Collection<ModuleEntity> modules;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Collection<ModuleEntity> getModules() {
        return modules;
    }

    public void setModules(Collection<ModuleEntity> modules) {
        this.modules = modules;
    }

    public void setAuthorities(Collection<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}
