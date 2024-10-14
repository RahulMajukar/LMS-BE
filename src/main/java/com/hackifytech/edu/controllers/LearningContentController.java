package com.hackifytech.edu.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.hackifytech.edu.models.LearningCategory;
import com.hackifytech.edu.models.LearningContent;
import com.hackifytech.edu.models.responsebodies.LearningContentResponse;
import com.hackifytech.edu.repositories.LearningCategoryRepository;
import com.hackifytech.edu.services.LearningContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-contents")
public class LearningContentController {

    @Autowired
    private LearningContentService learningContentService;
    
    @Autowired
    private LearningCategoryRepository learningCategoryrepo;

    @GetMapping
    public ResponseEntity<List<LearningContent>> getAllLearningContents() {
        return ResponseEntity.ok(learningContentService.getAllLearningContents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningContent> getLearningContentById(@PathVariable Long id) {
        return learningContentService.getLearningContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{title}")
    public ResponseEntity<LearningContent> findByTitle(@PathVariable String title) {
        return learningContentService.getLearningContentByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LearningContent> createLearningContent(@RequestBody LearningContent learningContent) {
        try {
            LearningContent createdContent = learningContentService.createLearningContent(learningContent);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdContent);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningContent> updateLearningContent(@PathVariable Long id, @RequestBody LearningContent learningContent) {
        try {
            return learningContentService.updateLearningContent(id, learningContent)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearningContent(@PathVariable Long id) {
        learningContentService.deleteLearningContent(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/category")
    public LearningCategory createCategory(@RequestBody LearningCategory catBody) {
    	return learningCategoryrepo.save(catBody);
    }
    
    // API endpoint to get all titles and ids by category
    //not working
    @GetMapping("/category/{categoryName}")
    public List<LearningContentResponse> getLearningContentByCategory(@PathVariable String categoryName) {
        return learningContentService.getTitlesAndIdsByCategory(categoryName);
    }
}
