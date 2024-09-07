package com.hackifytech.edu.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hackifytech.edu.models.requestbodies.LoginRequestBody;
import com.hackifytech.edu.repositories.UserRepository;
import com.hackifytech.edu.security.JwtTokenProvider;

@Service
public class AuthService {

	private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthService(
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(LoginRequestBody loginReqbody) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        		loginReqbody.getUsernameOrEmail(), loginReqbody.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
	
}
