package com.homestay.booking.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.userdetails.UserDetails;

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

    // Lấy username từ token
    public String extractUsername(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody().getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    // Kiểm tra token còn hợp lệ (đơn giản)
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        if (username == null) return false;
        return username.equals(userDetails.getUsername());
    }
}