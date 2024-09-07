package com.hackifytech.edu.services;

import com.hackifytech.edu.models.CourseQuiz;
import com.hackifytech.edu.models.Courses;
import com.hackifytech.edu.repositories.CourseQuizRepository;
import com.hackifytech.edu.repositories.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;
    
    @Autowired
    private CourseQuizRepository courseQuizRepo;

    public Courses createCourse(Courses course) {
        return coursesRepository.save(course);
    }

    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    public Optional<Courses> getCourseById(Long id) {
        return coursesRepository.findById(id);
    }

    public Courses updateCourse(Long id, Courses course) {
        if (coursesRepository.existsById(id)) {
            course.setId(id);
            return coursesRepository.save(course);
        }
        return null;
    }

    public void deleteCourse(Long id) {
        if (coursesRepository.existsById(id)) {
            coursesRepository.deleteById(id);
        }
    }
    
//    Course Quiz Services
    
    public List<CourseQuiz> getAllQuestions() {
        return courseQuizRepo.findAll();
    }

    public CourseQuiz saveQuestion(CourseQuiz question) {
        return courseQuizRepo.save(question);
    }

    public CourseQuiz getQuestionById(Long id) {
        return courseQuizRepo.findById(id).orElse(null);
    }

    public void deleteQuestion(Long id) {
    	courseQuizRepo.deleteById(id);
    }
}
