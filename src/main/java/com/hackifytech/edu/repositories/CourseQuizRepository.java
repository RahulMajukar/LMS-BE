package com.hackifytech.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackifytech.edu.models.CourseQuiz;

public interface CourseQuizRepository  extends JpaRepository<CourseQuiz, Long> {

}
