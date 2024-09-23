package com.hackifytech.edu.controllers;

import com.hackifytech.edu.models.ContactUs;
import com.hackifytech.edu.models.User;
import com.hackifytech.edu.models.responsebodies.UserResponseBody;
import com.hackifytech.edu.repositories.ContactUsRepository;
import com.hackifytech.edu.repositories.UserRepository;
import com.hackifytech.edu.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app")
@CrossOrigin(origins = "http://localhost:5173")
public class AppController {

    @Autowired
    private ContactUsRepository contactUsRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; 
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private AppService appService;

    // POST API to save ContactUs message
    @PostMapping("/contact-us")
    public ResponseEntity<ContactUs> createContactMessage(@RequestBody ContactUs contactUs) {
        return ResponseEntity.ok(contactUsRepository.save(contactUs));
    }

    // GET API to retrieve all ContactUs messages
//    @GetMapping("/contact-us")
//    public ResponseEntity<List<ContactUs>> getAllMessages() {
//        return ResponseEntity.ok( contactUsRepository.findAll());
//    }
    
    @PostMapping("/createuser")
    public ResponseEntity<UserResponseBody> addUser(@RequestBody User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        User savedUser = userRepo.save(user);
        return ResponseEntity.ok(new UserResponseBody(200, "User created successfully", savedUser));
    }
    
    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestParam String email) {
        try {
            appService.generateOtp(email);
            return ResponseEntity.ok("OTP sent to your email");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestParam String email, @RequestParam String otpCode) {
        boolean isValid = appService.validateOtp(email, otpCode);
        if (isValid) {
            return ResponseEntity.ok("OTP is valid");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
    }

    // Endpoint to reset the password using OTP
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String otpCode, @RequestParam String newPassword) {
        try {
            appService.resetPassword(email, otpCode, newPassword);
            return ResponseEntity.ok("Password reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
