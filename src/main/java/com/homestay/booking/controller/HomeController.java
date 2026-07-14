package com.homestay.booking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "Chúc mừng bạn! Project Spring Boot đặt phòng Homestay đã chạy thành công!";
    }
}