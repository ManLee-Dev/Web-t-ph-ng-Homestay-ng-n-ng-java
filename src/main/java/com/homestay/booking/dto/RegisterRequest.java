package com.homestay.booking.dto;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String roleName; // Chọn quyền khi đăng ký: ROLE_SHOP hoặc ROLE_USER
}