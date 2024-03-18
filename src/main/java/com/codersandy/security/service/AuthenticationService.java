package com.codersandy.security.service;

import com.codersandy.security.auth.AuthenticationRequest;
import com.codersandy.security.auth.AuthenticationResponse;
import com.codersandy.security.auth.RegisterRequest;
import com.codersandy.security.entity.User;

public interface AuthenticationService {
  User register(RegisterRequest register);
  AuthenticationResponse authenticate(AuthenticationRequest request);
}
