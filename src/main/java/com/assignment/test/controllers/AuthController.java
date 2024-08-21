package com.assignment.test.controllers;

import com.assignment.test.auth.AuthService;
import com.assignment.test.auth.JWTService;
import com.assignment.test.auth.RefreshTokenService;
import com.assignment.test.dtos.AuthResponse;
import com.assignment.test.dtos.LoginRequest;
import com.assignment.test.dtos.RegisterRequest;
import com.assignment.test.dtos.UserDto;
import com.assignment.test.entities.RefreshToken;
import com.assignment.test.entities.User;
import com.assignment.test.utils.UserMappers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JWTService jwtService;
    private final UserMappers userMappers;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest registerRequest) {
        UserDto response = userMappers.repoToDto(authService.register(registerRequest));
        response.setMessage("Please login to get authorized!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshTokenRequest) {

        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest);
        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenRequest)
                .build());
    }
}
