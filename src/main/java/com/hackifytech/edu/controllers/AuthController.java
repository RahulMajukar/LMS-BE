package com.hackifytech.edu.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hackifytech.edu.models.User;
import com.hackifytech.edu.models.requestbodies.LoginRequestBody;
import com.hackifytech.edu.models.responsebodies.JWTAuthResponse;
//import com.hackifytech.edu.models.responsebodies.JWTAuthResponse;
import com.hackifytech.edu.services.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticate(@RequestBody LoginRequestBody loginRequestBody) {
//        String token = authService.login(loginRequestBody);
//        return ResponseEntity.ok(token);
//    }
    
    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginRequestBody loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
    

}
