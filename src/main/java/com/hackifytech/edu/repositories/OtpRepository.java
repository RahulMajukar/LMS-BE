package com.hackifytech.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackifytech.edu.models.Otp;
import com.hackifytech.edu.models.User;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findTopByUserOrderByCreatedAtDesc(User user); // Get the latest OTP for a user
}
