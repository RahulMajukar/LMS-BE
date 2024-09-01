package com.hackifytech.edu.models.requestbodies;

import lombok.Data;

@Data
public class LoginRequestBody {
	String email;
	String password;
}
