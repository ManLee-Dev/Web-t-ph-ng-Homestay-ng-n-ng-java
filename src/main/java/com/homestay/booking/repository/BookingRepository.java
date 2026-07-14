package com.homestay.booking.repository;

import com.homestay.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Tự động sinh ra hàm tìm User bằng Username phục vụ cho việc đăng nhập sau này
    
}