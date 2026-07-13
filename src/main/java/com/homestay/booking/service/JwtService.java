package com.homestay.booking.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    // Chìa khóa bí mật để ký token (Tuyệt đối không để lộ)
    // Chú ý: Chuỗi này phải đủ dài (ít nhất 256-bit) cho thuật toán HS256
    private static final String SECRET_KEY = "DayLaMotChuoiBaoMatCucKyDaiChoDuAnHomestayBookingNhe!";
    // Thời gian sống của token (Ví dụ: 24 giờ)
    private static final long EXPIRATION_TIME = 86400000;

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Hàm tạo Token dựa trên username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}