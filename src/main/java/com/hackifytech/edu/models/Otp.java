package com.hackifytech.edu.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "OTP is required")
    private String otpCode;

    // Time when OTP was created
    private LocalDateTime createdAt;

    // Many OTPs can be associated with a single User
    @ManyToOne
    private User user;

    // Initialize createdAt when an OTP is generated
    public Otp() {
        this.createdAt = LocalDateTime.now();
    }

    // Check if the OTP is still valid (valid for 5 minutes)
    public boolean isValid() {
        return this.createdAt.isAfter(LocalDateTime.now().minusMinutes(5));
    }
}