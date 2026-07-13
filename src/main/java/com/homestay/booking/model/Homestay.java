package com.homestay.booking.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "homestays")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Homestay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private BigDecimal pricePerNight; // Giá tiền mỗi đêm (Dùng BigDecimal để tránh sai số tiền tệ)

    private String imageUrl; // Link ảnh đại diện của homestay

    @Column(nullable = false)
    private String status; // Trạng thái: AVAILABLE (Còn trống), PENDING (Đang chờ duyệt), LOCKED (Bị khóa)

    // Liên kết với bảng Users (Chủ homestay)
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}