package com.homestay.booking.service;

import com.homestay.booking.dto.RegisterRequest;
import com.homestay.booking.model.Role;
import com.homestay.booking.model.User;
import com.homestay.booking.repository.RoleRepository;
import com.homestay.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        // Kiểm tra username đã tồn tại chưa
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Lỗi: Tên đăng nhập đã tồn tại!";
        }

        // Tìm Role trong DB (nếu chưa có thì tạo mới để test)
        Role role = roleRepository.findByName(request.getRoleName())
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(request.getRoleName());
                    return roleRepository.save(newRole);
                });

        // Tạo user mới
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        userRepository.save(user);
        return "Đăng ký tài khoản thành công!";
    }
}