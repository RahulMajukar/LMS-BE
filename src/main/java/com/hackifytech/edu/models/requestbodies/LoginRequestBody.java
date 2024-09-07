package com.hackifytech.edu.models.requestbodies;

import lombok.Data;

@Data
public class LoginRequestBody {
    private String usernameOrEmail;
    private String password;
}
