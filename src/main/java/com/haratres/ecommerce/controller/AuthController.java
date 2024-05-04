package com.haratres.ecommerce.controller;

import com.haratres.ecommerce.config.SignUpValidator;
import com.haratres.ecommerce.controller.request.SignInRequest;
import com.haratres.ecommerce.controller.request.SignUpRequest;
import com.haratres.ecommerce.controller.response.AuthResponse;
import com.haratres.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final SignUpValidator signUpValidator;

    public AuthController(AuthService authService, SignUpValidator signUpValidator) {
        this.authService = authService;
        this.signUpValidator = signUpValidator;
    }

    @InitBinder(value = "signUpRequest")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(signUpValidator);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@Valid @RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }

}
