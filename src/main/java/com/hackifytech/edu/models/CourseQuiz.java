package com.hackifytech.edu.models;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class CourseQuiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Question is mandatory")
	private String question;

	@ElementCollection
	private List<String> options;

	@Lob
	@NotBlank(message = "Answer is mandatory")
	private String answer; // Corrected type to String
}
