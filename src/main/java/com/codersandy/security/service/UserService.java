package com.codersandy.security.service;

import com.codersandy.security.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
