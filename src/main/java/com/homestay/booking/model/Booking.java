package com.homestay.booking.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Người thực hiện đặt phòng
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    // Homestay được đặt
    @ManyToOne
    @JoinColumn(name = "homestay_id", nullable = false)
    private Homestay homestay;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private BigDecimal totalPrice; // Tổng tiền khách phải trả

    @Column(nullable = false)
    private String bookingStatus; // Trạng thái đơn: PENDING (Chờ duyệt), CONFIRMED (Đã xác nhận), CANCELLED (Đã hủy)

    @Column(nullable = false)
    private String paymentStatus; // Trạng thái thanh toán: UNPAID (Chưa trả), PAID (Đã thanh toán qua VNPay/Momo)
}