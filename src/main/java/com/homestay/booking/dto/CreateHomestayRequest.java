package com.homestay.booking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateHomestayRequest {
    private String name;
    private String description;
    private String address;
    private BigDecimal pricePerNight;
    private String imageUrl;
    private String status;
    private Long ownerId;
}
