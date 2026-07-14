package com.homestay.booking.controller;

import com.homestay.booking.dto.RegisterRequest;
import com.homestay.booking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.homestay.booking.dto.LoginRequest;
import com.homestay.booking.dto.AuthResponse;
import com.homestay.booking.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    // Xác thực tài khoản và mật khẩu
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );

    // Nếu không có lỗi (sai mật khẩu sẽ quăng Exception tự động), thì tạo JWT token
    String token = jwtService.generateToken(authentication.getName());
    
    // Trả về token cho Client
    return ResponseEntity.ok(new AuthResponse(token));
}

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String response = authService.register(request);
        if (response.startsWith("Lỗi")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}