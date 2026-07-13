package com.homestay.booking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data // Của thư viện Lombok, tự động tạo Get/Set
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    // Nhiều User có thể có chung 1 Role (ví dụ: nhiều người làm Customer)
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}