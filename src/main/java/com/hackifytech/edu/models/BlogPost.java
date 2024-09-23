package com.hackifytech.edu.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

	@Lob
    @Column(name = "poster", nullable = true)
    private byte[] poster;
	
	@Column(nullable = false, length = 55, unique = true)
	private String slug;
    
    @Column(nullable = false, length = 1000)
    private String content;
    
//	@Column(columnDefinition = "TEXT")
//	private String content;

    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false)
    private String category;  
    
    
    private String[] tags;
    
    
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
	
	
}
