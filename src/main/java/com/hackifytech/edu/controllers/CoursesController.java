package com.hackifytech.edu.controllers;

import com.hackifytech.edu.models.CourseQuiz;
import com.hackifytech.edu.models.Courses;
import com.hackifytech.edu.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @PostMapping
    public ResponseEntity<Courses> createCourse(
            @RequestParam("courseName") String courseName,
            @RequestParam("description") String description,
            @RequestParam("duration") String duration,
            @RequestParam("prize") float prize,
            @RequestParam("offeredprize") float offeredPrize,
            @RequestParam(value = "syllabus", required = false) MultipartFile syllabus,
            @RequestParam(value = "courseImage", required = false) MultipartFile courseImage
    ) {
        Courses course = new Courses();
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDuration(duration);
        course.setPrize(prize);
        course.setOfferedprize(offeredPrize);

//        try {
//            if (syllabus != null && !syllabus.isEmpty()) {
//                course.setSyllabus(syllabus.getBytes());
//            }
//            if (courseImage != null && !courseImage.isEmpty()) {
//                course.setCourseImage(courseImage.getBytes());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Courses createdCourse = coursesService.createCourse(course);
        return ResponseEntity.ok(createdCourse);
    }

    @GetMapping
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> courses = coursesService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCourseById(@PathVariable Long id) {
        Optional<Courses> course = coursesService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Courses> updateCourse(@PathVariable Long id, @RequestBody Courses course) {
        Courses updatedCourse = coursesService.updateCourse(id, course);
        return updatedCourse != null ? ResponseEntity.ok(updatedCourse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        coursesService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    
//    Course Quiz:
    @GetMapping("/quiz")
    public ResponseEntity<List<CourseQuiz>> getAllQuestions() {
        List<CourseQuiz> questions = coursesService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // Get a question by ID
    @GetMapping("/quiz/{id}")
    public ResponseEntity<CourseQuiz> getQuestionById(@PathVariable Long id) {
    	CourseQuiz question = coursesService.getQuestionById(id);
        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
    }

    // Create a new question
    @PostMapping("/quiz")
    public ResponseEntity<CourseQuiz> createQuestion(@RequestBody CourseQuiz question) {
    	CourseQuiz savedQuestion = coursesService.saveQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    // Delete a question by ID
    @DeleteMapping("/quiz/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    	coursesService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
