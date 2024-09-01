package com.hackifytech.edu.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class User {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long id;
	
	@NotBlank(message = "Name is mandatory")
	private String userName;
	
	@NotBlank(message = "Name is mandatory")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@NotNull
	private long phone;
	
	@JsonIgnore
	private boolean isDeleted=false;
	
	@JsonIgnore
	@Column(nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

	@JsonIgnore
	@LastModifiedDate
	private LocalDateTime updatedAt;
    
    @JsonIgnore
    private LocalDateTime lastLogin; 
	
}
