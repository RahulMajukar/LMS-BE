package com.hackifytech.edu.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Assuming you are using Spring Security for password encryption

import com.hackifytech.edu.models.Otp;
import com.hackifytech.edu.models.User;
import com.hackifytech.edu.repositories.OtpRepository;
import com.hackifytech.edu.repositories.UserRepository;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private OtpRepository otpRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CommonServices commonService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    public void generateOtp(String email) {
        User user = userRepository.findByEmail(email);

        // Ensure user exists
        if (user == null) {
            throw new RuntimeException("User not found with the provided email");
        }

        // Generate random OTP
        String otpCode = generateRandomOtp();

        // Check if an OTP already exists for the user
        Otp otp = otpRepository.findTopByUserOrderByCreatedAtDesc(user);

        if (otp != null) {
            // Update existing OTP and timestamp
            otp.setOtpCode(otpCode);
            otp.setCreatedAt(LocalDateTime.now());
        } else {
            // Create a new OTP if none exists
            otp = new Otp();
            otp.setOtpCode(otpCode);
            otp.setUser(user);
        }

        // Save the OTP to the database
        otpRepository.save(otp);

        System.err.println(otpCode + " is the OTP for " + user.getEmail());
        String htmlOtp = commonService.otpTemplate(user,otpCode);

        // Send OTP via email
        try {
            emailService.sendHtmlEmail(user.getEmail(), "Password Reset OTP", htmlOtp);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error while sending OTP in AppService.generateOtp");
        }
    }


    public boolean validateOtp(String email, String otpCode) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            Otp otp = otpRepository.findTopByUserOrderByCreatedAtDesc(user);
            if (otp != null && otp.getOtpCode().equals(otpCode) && otp.isValid()) {
                return true;
            }
        }
        return false;
    }

    public void resetPassword(String email, String otpCode, String newPassword) {
        if (validateOtp(email, otpCode)) {
            User user = userRepository.findByEmail(email);
            // Hash the password before saving
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new RuntimeException("Invalid or expired OTP");
        }
    }

    private String generateRandomOtp() {
        return String.format("%04d", new Random().nextInt(9999)); // Generates a 4-digit OTP
    }
}
