package com.hackifytech.edu.repositories;

import com.hackifytech.edu.models.LearningContent;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningContentRepository extends JpaRepository<LearningContent, Long> {

	Optional<LearningContent> findByTitle(String title);
	
    // Custom query to find id and title based on the category name
    @Query("SELECT lc.id, lc.title FROM LearningContent lc WHERE lc.category.name = :categoryName AND lc.isDeleted = false AND lc.isApproved = true")
    List<Object[]> findIdAndTitleByCategory(String categoryName);
}
