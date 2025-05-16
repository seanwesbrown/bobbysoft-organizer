package com.example.application.usermanagement.dto;

import java.util.Collection;

public record UserInfoDto(String name, Collection<String> authorities, String email, String avatarUrl) {
}
