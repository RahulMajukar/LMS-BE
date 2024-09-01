package com.hackifytech.edu.models.responsebodies;

import com.hackifytech.edu.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseBody {
	int httpStatusCode;
	String message;
	User user;
}
