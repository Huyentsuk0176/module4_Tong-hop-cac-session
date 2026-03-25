package com.example.employee_api.auth;

import com.example.employee_api.dto.request.LoginRequest;
import com.example.employee_api.dto.request.RegisterRequest;
import com.example.employee_api.dto.response.LoginResponse;
import com.example.employee_api.security.jwt.JwtProvider;
import com.example.employee_api.security.principal.UserPrincipal;
import com.example.employee_api.security.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtProvider jwtProvider,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpSession session) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userPrincipal);

        session.setAttribute("username", userPrincipal.getUsername());

        return new LoginResponse(
                token,
                "Bearer",
                userPrincipal.getUsername()
        );
    }
}