package com.example.secutiryjwt.config.auth;

import com.example.secutiryjwt.config.JwtService;
import com.example.secutiryjwt.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthenticationResponse register(RegisterRequest request) {
     var user = User.builder()
             .firstname(request.getFirstname())
             .lastname(request.getLastname())
             .email(request.getEmail())
             .password(passwordEncoder.encode(request.getPassword()))
             .role(Role.USER)
             .build();
           repository.save(user);
           var jwtToken = jwtService.generateToken(user);
           return AuthenticationResponse.builder()
                   .token(jwtToken)
                   .build();


        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
     return null;
    }
}
