package com.hackifytech.edu.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JavaMailSender mailSender;

//    private Map<String, String> otpStorage = new HashMap<>();
    private Map<String, String> otpStorage = new HashMap<>();

    public String generateOTP(String email) {
        String otp = String.valueOf(new Random().nextInt(9999));
        otpStorage.put(email, otp);
//        sendOTPEmail(email, otp);
        return otp;
    }

    public boolean verifyOTP(String email, String otp) {
        return otp.equals(otpStorage.get(email));
    }

    public void sendOTPEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);
    }

    public void clearOTP(String email) {
        otpStorage.remove(email);
    }
}
