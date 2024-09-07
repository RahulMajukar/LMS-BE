package com.hackifytech.edu.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.hackifytech.edu.models.User;
import com.hackifytech.edu.models.requestbodies.LoginRequestBody;
import com.hackifytech.edu.models.responsebodies.UserResponseBody;
import com.hackifytech.edu.repositories.UserRepository;
import com.hackifytech.edu.services.CommonServices;
import com.hackifytech.edu.services.EmailService;
import com.hackifytech.edu.services.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommonServices commonServices;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder; 

    // Get All Users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(users);
    }

    // Register a New User
//    @PostMapping("/")
//    public ResponseEntity<UserResponseBody> addUser(@RequestBody User user) {
//        User savedUser = userRepo.save(user);
//        return ResponseEntity.ok(new UserResponseBody(200, "User created successfully", savedUser));
//    }
    
    @PostMapping("/")
    public ResponseEntity<UserResponseBody> addUser(@RequestBody User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        User savedUser = userRepo.save(user);
        return ResponseEntity.ok(new UserResponseBody(200, "User created successfully", savedUser));
    }

    // Login a User
    @PostMapping("/login")
    public ResponseEntity<UserResponseBody> login(@RequestBody LoginRequestBody loginRequest) {
        User dbUser = userRepo.findByEmail(loginRequest.getUsernameOrEmail());

        if (dbUser != null) {
            if (loginRequest.getPassword().equals(dbUser.getPassword())) {
            	dbUser.setLastLogin(LocalDateTime.now()); // Set the last login time
                userRepo.save(dbUser);
                return ResponseEntity.ok(new UserResponseBody(200, "Login successful", dbUser));
            } else {
                return ResponseEntity.status(401).body(new UserResponseBody(401, "Invalid password", null));
            }
        } else {
            return ResponseEntity.status(404).body(new UserResponseBody(404, "User not found", null));
        }
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String email) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            String otp = userService.generateOTP(email);
            System.out.println("OTP is: "+otp);
            return ResponseEntity.ok("OTP sent to your email.");
        }
        return ResponseEntity.status(404).body("User not found.");
    }
    
//    otpTemplate
    @PostMapping("/sendotp")
    public ResponseEntity<String> sendotp(@RequestBody String email) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            String otp = userService.generateOTP(email);
            String otpHtml=commonServices.otpTemplate(otp);
            try {
				emailService.sendHtmlEmail("haleshkhaddi501@gmail.com","sample mail" , otpHtml);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
            System.out.println("OTP is: "+otp);
            return ResponseEntity.ok("OTP sent to your email.");
        }
        return ResponseEntity.status(404).body("User not found.");
    }
    

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        if (userService.verifyOTP(email, otp)) {
            return ResponseEntity.ok("OTP verified. You can now reset your password.");
        }
        return ResponseEntity.status(400).body("Invalid OTP.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        User user = userRepo.findByEmail(email);
        if (user != null) {
            user.setPassword(newPassword);
            userRepo.save(user);
            userService.clearOTP(email);
            return ResponseEntity.ok("Password reset successfully.");
        }
        return ResponseEntity.status(404).body("User not found.");
    }
    
    
    
    
    
}
