package com.homestay.booking.repository;

import com.homestay.booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Tự động sinh ra hàm tìm User bằng Username phục vụ cho việc đăng nhập sau này
    Optional<User> findByUsername(String username);
}