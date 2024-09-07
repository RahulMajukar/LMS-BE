package com.hackifytech.edu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long id;

	@Column(name = "course_name", nullable = false, length = 255)
	private String courseName;

	@Column(columnDefinition = "TEXT")
	private String description;

	private String duration;

	private float prize;

	private float offeredprize;

//	@Lob
//	@Column(name = "syllabus", nullable = true)
//	private byte[] syllabus;
//
//	@Lob
//	@Column(name = "course_image", nullable = true)
//	private byte[] courseImage;

	@JsonIgnore
	private boolean isDeleted = false;

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
	private Long createdBy;
	
	@JsonIgnore
	private Long updatedBy;
}