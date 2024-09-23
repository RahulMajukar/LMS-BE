package com.hackifytech.edu.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.JoinColumn;
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
	private String username;
	
	@NotBlank(message = "Name is mandatory")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false)
//	@Email
	private String email;
	
	@Lob
    @Column(name = "profile_image", nullable = true)
    private byte[] profileImage;
	
	@NotNull
	private long phone;
	
	@JsonIgnore
	private boolean isDeleted=false;
	
	private String gender;
	
    @ManyToMany
    @JoinTable(
        name = "user_courses", 
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Courses> courses;
	
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
	
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles",
//        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
