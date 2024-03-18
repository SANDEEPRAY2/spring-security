package com.codersandy.security.service.impl;

import com.codersandy.security.entity.User;
import com.codersandy.security.repository.UserRepository;
import com.codersandy.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
