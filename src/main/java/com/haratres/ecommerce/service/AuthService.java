package com.haratres.ecommerce.service;


import com.haratres.ecommerce.controller.request.SignInRequest;
import com.haratres.ecommerce.controller.request.SignUpRequest;
import com.haratres.ecommerce.controller.response.AuthResponse;
import com.haratres.ecommerce.domain.User;
import com.haratres.ecommerce.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse signup(SignUpRequest request) {
        User user = User.UserBuilder.anUser()
                .withEmail(request.getEmail())
                .withPassword(passwordEncoder.encode(request.getPassword()))
                .withName(request.getName())
                .withSurname(request.getSurname())
                .withPhoneNumber(request.getPhoneNumber())
                .withRole(request.getRole())
                .build();

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }


    public AuthResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }


}
