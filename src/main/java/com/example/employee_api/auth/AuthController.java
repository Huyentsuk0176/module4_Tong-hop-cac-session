package com.example.employee_api.auth;

import com.example.employee_api.dto.request.LoginRequest;
import com.example.employee_api.dto.request.RegisterRequest;
import com.example.employee_api.repository.UserRepository;
import com.example.employee_api.security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return null;
    }

    private final AuthService authService;



    @GetMapping("/hello")
    public String hello() {
        return "Public API - no need login";
    }

   // @PostMapping("/login")
    //public String login() {
    //    return "Dang nhap thanh cong";
   // }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}