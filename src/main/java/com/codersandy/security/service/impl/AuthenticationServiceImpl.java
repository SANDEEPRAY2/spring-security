package com.codersandy.security.service.impl;

import com.codersandy.security.auth.AuthenticationRequest;
import com.codersandy.security.auth.AuthenticationResponse;
import com.codersandy.security.auth.RegisterRequest;
import com.codersandy.security.config.JwtService;
import com.codersandy.security.entity.Role;
import com.codersandy.security.entity.User;
import com.codersandy.security.repository.UserRepository;
import com.codersandy.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    @Override
    public User register(RegisterRequest register) {
        var user= User.builder()
                .firstName(register.getFirstname())
                .lastName(register.getFirstname())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(Role.USER)
                .build();
            return userRepository.save(user);

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
