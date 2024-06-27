package com.hawa.trucks.order.dto;


import com.hawa.trucks.order.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserProfileDTO user;
}
