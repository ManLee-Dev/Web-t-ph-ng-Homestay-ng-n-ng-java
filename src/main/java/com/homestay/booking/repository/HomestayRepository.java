package com.homestay.booking.repository;

import com.homestay.booking.model.Homestay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HomestayRepository extends JpaRepository<Homestay, Long> {
    // Tự động sinh ra hàm tìm Homestay bằng tên phục vụ cho việc tìm kiếm sau này
    Optional<Homestay> findByName(String name);
}