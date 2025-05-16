package com.example.application.usermanagement.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    public static final int USER_MAX_LENGTH = 50;
    public static final int ENCODED_PASSWORD_MAX_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "username", nullable = false)
    @Size(max = USER_MAX_LENGTH)
    private String username;

    @Column(name = "password", nullable = false)
    @Size(max = ENCODED_PASSWORD_MAX_LENGTH)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;
}
