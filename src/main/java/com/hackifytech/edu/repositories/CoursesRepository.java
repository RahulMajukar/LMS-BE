package com.hackifytech.edu.repositories;

import com.hackifytech.edu.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    // Custom query methods can be added here if needed
}
