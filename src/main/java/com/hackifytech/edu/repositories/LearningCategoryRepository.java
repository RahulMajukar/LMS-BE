package com.hackifytech.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackifytech.edu.models.LearningCategory;

public interface LearningCategoryRepository extends JpaRepository<LearningCategory, Long>{

}
