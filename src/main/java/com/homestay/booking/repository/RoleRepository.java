package com.homestay.booking.repository;

import com.homestay.booking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Tự động sinh ra hàm tìm Role bằng tên phục vụ cho việc đăng ký sau này
    Optional<Role> findByName(String name);
}